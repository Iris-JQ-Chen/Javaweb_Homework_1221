package servlet;

import bean.institute;
import dao.DBInstitute;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "InstituteServlet", urlPatterns = "/institute")
public class InstituteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        String instituteName = request.getParameter("name");
        institute instituteBean = DBInstitute.queryInstituteByInstituteName(instituteName);
        int instituteType = new Integer(request.getParameter("type"));
        if (instituteType == 1){        //修改辅导员
            String newCoachName = request.getParameter("coach");
            String newCoachTel = request.getParameter("coachTel");
            if (DBInstitute.changeCoachAndCoachTel(instituteBean.getInstituteNo(),newCoachName,newCoachTel)){
                printWriter.flush();
                printWriter.print("Success");
            }
        } else if (instituteType == 2){ //修改电话
            String newCoachTel = request.getParameter("coachTel");
            if (DBInstitute.changeCoachTel(instituteBean.getInstituteNo(),newCoachTel)){
                printWriter.flush();
                printWriter.print("Success");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();

        printWriter.println("doGet");
    }
}
