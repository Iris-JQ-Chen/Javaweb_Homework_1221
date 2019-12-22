package servlet;

import dao.DBUserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChangePwdServlet", urlPatterns = "/ChangePwd")
public class ChangePwdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("charset = utf-8");
        PrintWriter printWriter = response.getWriter();

        String pp = request.getParameter("pp");
        String name = new String();
        String password = new String();
        String limit = new String();

        Boolean isReady = false;

        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if ("name".equalsIgnoreCase(cookie.getName())){
                    name = cookie.getValue();
                } else if ("password".equalsIgnoreCase(cookie.getName())){
                    password = cookie.getValue();
                } else if ("limit".equalsIgnoreCase(cookie.getName())){
                    limit = cookie.getValue();
                }
            }
            if ((!"".equalsIgnoreCase(name)) && (!"".equalsIgnoreCase(password)) && (!"".equalsIgnoreCase(limit)) && (!"".equalsIgnoreCase(pp))){
                isReady = true;
            }
        }

        if (isReady){
            if (DBUserInfo.changeUserPassword(name,pp,limit)){
                printWriter.println("Success");
            } else {
                printWriter.println("Failure");
            }
        } else {
            printWriter.println("Failure");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("doGet  ChangePwd");
    }
}
