package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.MirrorServlet;
import servlets.SessionsServlet;
import servlets.UsersServlet;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new UsersServlet(accountService)),"/api/v1/users");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)),"/api/v1/sessions");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("publicHTML");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        Logger.getGlobal().info("Server started");
        server.join();

//        old
        MirrorServlet mirrorServlet = new MirrorServlet();
        context.addServlet(new ServletHolder(mirrorServlet),"/mirror");

    }
}
