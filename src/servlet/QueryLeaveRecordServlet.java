package servlet;

import bean.leaveRecord;
import com.google.gson.Gson;
import dao.DBLeaveRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "QueryLeaveRecordServlet", urlPatterns = "/QueryLeaveRecord")
public class QueryLeaveRecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        String studentNo = request.getParameter("studentNo");
        List<leaveRecord> leaveRecordList = new LinkedList<>();

        if (!"".equalsIgnoreCase(studentNo) && null != studentNo){
            leaveRecordList = DBLeaveRecord.queryLeaveRecordByStudentNo(studentNo);

            Gson gson = new Gson();
            printWriter.print(gson.toJson(leaveRecordList));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.print("doGet");
    }
}
