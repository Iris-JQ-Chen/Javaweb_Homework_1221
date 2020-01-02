package servlet;

import dao.DBStudentInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StudentServlet", urlPatterns = "/StudentTransaction")
public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        request.setCharacterEncoding("utf-8");

        String studentNo = request.getParameter("changeStudentNo");
        String studentName = request.getParameter("changeStudentName");
        String studentSex = request.getParameter("changeStudentSex");
        String instituteNo = request.getParameter("changeInstituteName");
        String studentGrad = request.getParameter("changeStudentGrade");
        String dormitoryNo = request.getParameter("changeDormitoryNo");
        String studentTel = request.getParameter("changeStudentTel");
        int iNo = 1;

        if ("物联网工程学院".equalsIgnoreCase(instituteNo)){
            iNo = 1;
        } else if ("企业管理学院".equalsIgnoreCase(instituteNo)){
            iNo = 2;
        } else if ("机电院".equalsIgnoreCase(instituteNo)){
            iNo = 3;
        }

        if (DBStudentInfo.changeStudentInfoByStudentNo(studentNo,iNo,dormitoryNo,studentTel)){
            printWriter.println("success");
            response.sendRedirect(request.getContextPath()+"/HomeAdmin.jsp?changeStudentInfo=true");
        } else {
            printWriter.println("failure");
            response.sendRedirect(request.getContextPath()+"/HomeAdmin.jsp?changeStudentInfo=false");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();

        printWriter.println("doGet");
    }
}
