package servlet;

import bean.dormitory;
import dao.DBDormitory;
import dao.DBHygieneRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "HygieneGradeServlet", urlPatterns = "/HygieneGrade")
public class HygieneGradeServlet extends HttpServlet {
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
        List<dormitory> dormitoryList = DBDormitory.queryDormitoryByManagerNo(userNo);

        for (dormitory dormitory: dormitoryList){
            printWriter.print(dormitory.getDormitoryNo());
            String grade = request.getParameter(dormitory.getDormitoryNo());
            if ("".equalsIgnoreCase(grade)){
            } else {
                String date = LocalDate.now().toString();
                DBHygieneRecord.addHygieneRecord(userNo,dormitory.getDormitoryNo(),grade , date);
            }
        }

        response.sendRedirect(request.getContextPath()+"/HomeDManager.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.print("doPost");
    }
}
