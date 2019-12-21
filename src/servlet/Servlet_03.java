package servlet;

import dao.DBUserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet_03", urlPatterns = "/Servlet_03")
public class Servlet_03 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String limit = request.getParameter("limit");

        PrintWriter printWriter = response.getWriter();

        if (DBUserInfo.changeUserPassword(userId,password,limit)){
            printWriter.println("修改成功");
        }

        printWriter.println("doPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();



        printWriter.println("doGet");
    }
}
