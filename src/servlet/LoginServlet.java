package servlet;

import dao.DBUserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//登录
@WebServlet(name = "LoginServlet", urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String userLimit = request.getParameter("optionsRadiosinline");

        if (DBUserInfo.userLogin(userName,userPassword,userLimit)){
            request.getSession().setAttribute("msg",userName+";"+userPassword+";"+userLimit);
            if (userLimit.equals("1")){         //管理员
                response.sendRedirect(request.getContextPath()+"/HomeAdmin.jsp");
            } else if (userLimit.equals("2")){  //宿管员
                response.sendRedirect(request.getContextPath()+"/HomeDManager.jsp");
            } else if (userLimit.equals("3")){  //学生
                response.sendRedirect(request.getContextPath()+"/HomeStudent.jsp");
            }
        }else {
            request.getSession().setAttribute("msg","登录失败");
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
 }
}
