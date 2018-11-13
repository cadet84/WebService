package main;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import java.util.logging.Logger;

public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        DBService dbService = new DBService();
        dbService.printConnectInfo();
        try {
            long userId =dbService.addUser("Anton");
            System.out.println("Added user Id " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set " + dataSet);

            dbService.cleanUp();
        }catch (DBException e){
            e.printStackTrace();
        }
//        AccountService accountService = new AccountService();
//
//        accountService.addNewUser(new UserProfile("admin"));
//        accountService.addNewUser(new UserProfile("test"));
//
//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
////        context.addServlet(new ServletHolder(new UsersServlet(accountService)),"/au");
////        context.addServlet(new ServletHolder(new SessionsServlet(accountService)),"/*");
//        context.addServlet(new ServletHolder(new SignUpServlet(accountService)),"/signup");
//        context.addServlet(new ServletHolder(new SignInServlet(accountService)),"/signin");
//
//        ResourceHandler resourceHandler = new ResourceHandler();
//        resourceHandler.setResourceBase("publicHTML");
//
//        HandlerList handlers = new HandlerList();
//        handlers.setHandlers(new Handler[] {resourceHandler, context});
//
//        Server server = new Server(8080);
//        server.setHandler(handlers);
//
//        server.start();
//        System.out.println("Server started");
//        log.info("Server started");
//        server.join();
//
//
//
////        old
////        MirrorServlet mirrorServlet = new MirrorServlet();
////        context.addServlet(new ServletHolder(mirrorServlet),"/mirror");
//
    }
}
