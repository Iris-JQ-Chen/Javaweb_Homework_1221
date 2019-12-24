package servlet;

import dao.DBDormitoryManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DManagerServlet", urlPatterns = "/DManagerTransaction")
public class DManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        String DManagerNo = request.getParameter("changeDManagerNo");
        String DManagerBuildingNo = request.getParameter("changeBuildingNo");
        String DManagerTel = request.getParameter("changeDManagerTel");

        if (DBDormitoryManager.changeDormitoryManagerInfoByManagerNo(DManagerNo,DManagerBuildingNo,DManagerTel)){
            printWriter.print("Success");
            response.sendRedirect(request.getContextPath()+"/HomeAdmin.jsp");
        } else {
            printWriter.print("Failure");
            response.sendRedirect(request.getContextPath()+"/HomeAdmin.jsp?changeDManagerInfo=false");
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
