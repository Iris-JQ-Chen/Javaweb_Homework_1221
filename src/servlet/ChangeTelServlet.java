package servlet;

import dao.DBAdminInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChangeTelServlet", urlPatterns = "/ChangeTel")
public class ChangeTelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("charset = utf-8");
        PrintWriter printWriter = response.getWriter();

        String tt = request.getParameter("tt");
        String adminNo = new String();

        Boolean isReady = false;

        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if ("name".equalsIgnoreCase(cookie.getName())){
                    adminNo = cookie.getValue();
                }
            }
            if (!"".equalsIgnoreCase(adminNo)){
                isReady = true;
            }
        }

        if (isReady){
            if (DBAdminInfo.changeAdminTel(adminNo,tt)){
                printWriter.print("Success");
            } else {
                printWriter.print("Failure");
            }
        } else {
            printWriter.print("Failure");
        }

        printWriter.print("doPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("charset = utf-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.print("doGet");
    }
}
