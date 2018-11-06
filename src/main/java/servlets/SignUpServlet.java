package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SignUpServlet extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {

        String login = request.getParameter("login");
        String password = request.getParameter ("password");


    }
}
