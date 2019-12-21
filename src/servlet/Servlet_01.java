package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet_01", urlPatterns = "/Servlet_01")
public class Servlet_01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentName = request.getParameter("stuName");

        //插入

//        // 设置响应内容类型
//        response.setContentType("text/html; charset = utf-8");
//
//        // 实际的逻辑是在这里
//        PrintWriter out = response.getWriter();
//        out.println("<h1>" + "doPost" + studentName + "</h1>");

        //删除操作

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=utf-8");

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("    <form action=\"Servlet_01\" method=\"post\">\n" +
                "      请输入学生姓名<input type=\"text\" name=\"stuName\" value=\"学生名\">\n" +
                "      <input type=\"submit\" value=\"Submit\">\n" +
                "    </form>");
    }
}
