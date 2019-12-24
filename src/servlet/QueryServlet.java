package servlet;

import bean.student;
import com.google.gson.Gson;
import dao.DBStudentInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "QueryServlet", urlPatterns = "/QueryAllUsers")
public class QueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        String studentNo = request.getParameter("studentNo");

        if (!"".equalsIgnoreCase(studentNo) && null != studentNo){
            student std = DBStudentInfo.queryStudentByStudentNo(studentNo);

            Gson gson = new Gson();
            printWriter.print(gson.toJson(std));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.println("doGet");
    }
}
