package servlet;

import dao.DBLeaveRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ApproveLeaveServlet", urlPatterns = "/ApproveLeave")
public class ApproveLeaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        int leaveId = new Integer(request.getParameter("leaveId"));

        if (DBLeaveRecord.approveLeave(leaveId)){
            printWriter.print("S");
        } else {
            printWriter.print("F");
        }

        printWriter.print("doPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.print("doGet");
    }
}
