package servlet;

import dao.DBDormitory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RetireDormitoryServlet", urlPatterns = "/RetireDormitory")
public class RetireDormitoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        String studentNo = request.getParameter("studentNo");

        int number = DBDormitory.querydormitorynumberbystudentno(studentNo);
        number--;

        if (DBDormitory.retireDormitory(studentNo,String.valueOf(number))){
            printWriter.print("success");
        } else {
            printWriter.print("failure");
        }

//        printWriter.print("doPost"+studentNo);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.print("doGet");
    }
}
