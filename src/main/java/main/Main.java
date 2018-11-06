package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.MirrorServlet;
import servlets.UsersServlet;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));





        MirrorServlet mirrorServlet = new MirrorServlet();
        UsersServlet usersServlet = new UsersServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(mirrorServlet),"/mirror");
        context.addServlet(new ServletHolder(usersServlet),"/signup");







        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        Logger.getGlobal().info("Server started");
        server.join();

    }
}
