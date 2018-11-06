package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInServlet extends HttpServlet {
    private final AccountService accountService;
    public SignInServlet(AccountService accountService){this.accountService = accountService;}

    public void doPost (HttpServletRequest request, HttpServletResponse response){
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserProfile profile= new UserProfile(login, password,login+"@ukr.net");

        if(login==null || password==null){
            response.setContentType("text/html; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        accountService.addSession(request.getRequestedSessionId(),profile);
        accountService.addNewUser(profile);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
