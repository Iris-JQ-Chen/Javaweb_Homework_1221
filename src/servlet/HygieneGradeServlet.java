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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "HygieneGradeServlet", urlPatterns = "/HygieneGrade")
public class HygieneGradeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        String h = "0;/HomeDManager.jsp";

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
            String grade = request.getParameter(dormitory.getDormitoryNo());
            if ("".equalsIgnoreCase(grade)){
            } else if (isNumeric(grade)){
                int gradeInt = new Integer(grade);
                if (gradeInt<0 || gradeInt >100){
                    printWriter.println("打分失败");
                    h = "3;/HomeDManager.jsp";
                    break;
                } else {
                    String date = LocalDate.now().toString();
                    DBHygieneRecord.addHygieneRecord(userNo,dormitory.getDormitoryNo(), grade, date);
                }
            } else {
                printWriter.println("打分失败");
                h = "3;/HomeDManager.jsp";
                break;
            }
        }

//        response.sendRedirect(redirectPath);
        response.setHeader("refresh", h);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.print("doPost");
    }

    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^-?[0-9]+"); //这个也行
//        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");//这个也行
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
