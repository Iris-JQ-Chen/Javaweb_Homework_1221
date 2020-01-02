package servlet;

import bean.dormitoryManager;
import bean.student;
import dao.DBLeaveRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "ApplyForLeaveServlet", urlPatterns = "/ApplyForLeave")
public class ApplyForLeaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        String userNo = new String();
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if ("name".equalsIgnoreCase(cookie.getName())) {
                    if (!"".equalsIgnoreCase(cookie.getName())){
                        userNo = cookie.getValue();
                    }
                }
            }
        }

        String studentNo = userNo;
        String leavePlace = request.getParameter("leavePlace");
        String leaveReason = request.getParameter("leaveReason");
        String leaveS = request.getParameter("leaveDate");
        String leaveExbackS = request.getParameter("leaveExbackDate");

        Date leaveDate = getDateByString(leaveS);
        Date leaveExbackDate = getDateByString(leaveExbackS);

        if (DBLeaveRecord.addLeaveRecord(studentNo,leavePlace,leaveReason,leaveDate,leaveExbackDate)){
            response.sendRedirect(request.getContextPath()+"/HomeStudent.jsp");
        } else {
            printWriter.print("doPost"+studentNo+leavePlace+leaveReason+leaveDate+leaveExbackDate);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.print("doGet");
    }

    private Date getDateByString(String s){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = new Date(simpleDateFormat.parse(s).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
