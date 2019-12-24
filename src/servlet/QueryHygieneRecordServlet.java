package servlet;

import bean.hygieneRecord;
import bean.leaveRecord;
import com.google.gson.Gson;
import dao.DBHygieneRecord;
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

@WebServlet(name = "QueryHygieneRecordServlet", urlPatterns = "/QueryHygieneRecord")
public class QueryHygieneRecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        String dormitoryNo = request.getParameter("dormitoryNo");
        List<hygieneRecord> hygieneRecordList = new LinkedList<>();

        if (!"".equalsIgnoreCase(dormitoryNo) && null != dormitoryNo){
            hygieneRecordList = DBHygieneRecord.queryByDormitoryNo(dormitoryNo);

            Gson gson = new Gson();
            printWriter.print(gson.toJson(hygieneRecordList));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.print("doGet");
    }
}
