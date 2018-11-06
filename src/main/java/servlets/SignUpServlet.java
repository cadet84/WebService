package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;
    public SignUpServlet(AccountService accountService){this.accountService = accountService;}

    public void doPost (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(login==null || password==null){
            response.setContentType("text/html; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);

        if(profile == null){
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        accountService.addSession(request.getRequestedSessionId(),profile);
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Authorized: "+login);



    }


    public void doGet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.getWriter().println("Authorized: ");
    }

}
