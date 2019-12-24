package servlet;

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

@WebServlet(name = "HygieneGradeServlet", urlPatterns = "/HygieneGrade")
public class HygieneGradeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        String dormitoryNo = request.getParameter("DormitoryNo");
        String hygieneGrade = request.getParameter("HygieneGrade");
        String managerNo = new String();
        String date = LocalDate.now().toString();

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if ("name".equalsIgnoreCase(cookie.getName())){
                managerNo = cookie.getValue();
            }
        }

        DBHygieneRecord.addHygieneRecord(managerNo,dormitoryNo,hygieneGrade, date);
        printWriter.print("doPost");

        response.sendRedirect(request.getContextPath()+"/HomeDManager.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.print("doPost");
    }
}
