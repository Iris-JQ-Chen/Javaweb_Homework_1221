package servlet;

import bean.dormitory;
import com.sun.org.apache.bcel.internal.generic.DUP;
import dao.DBStudentInfo;
import dao.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "AllotDormitoryServlet", urlPatterns = "/AllotDormitory")
public class AllotDormitoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        String[] chooses = request.getParameterValues("inputCheckbox");

        int num1 = chooses.length / 4;
        int num2 = chooses.length % 4;

        if (fun(num1,num2,chooses)){
            response.sendRedirect(request.getContextPath()+"HomeAdmin.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.print("doGet");
    }

    private Boolean fun(int num1, int num2, String[] studentNo){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from Dormitory where number = 0";
        int i = 0;
        List<Integer> dormitoryNoList = new LinkedList<>();
        int anotherDormitoryNo = 0;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                i++;
                if (i == num1 + 1){
                    anotherDormitoryNo = new Integer(resultSet.getString("dormitoryNo"));
                    break;
                }
                dormitoryNoList.add(new Integer(resultSet.getString("dormitoryNo")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        int j;
        for (j = 0 ; j < dormitoryNoList.size() ; j++){
            updateDormitory(String.valueOf(dormitoryNoList.get(j)));

            DBStudentInfo.changeStudentDormitoryNoByStudentNo(studentNo[j*4+0],String.valueOf(dormitoryNoList.get(j)));
            DBStudentInfo.changeStudentDormitoryNoByStudentNo(studentNo[j*4+1],String.valueOf(dormitoryNoList.get(j)));
            DBStudentInfo.changeStudentDormitoryNoByStudentNo(studentNo[j*4+2],String.valueOf(dormitoryNoList.get(j)));
            DBStudentInfo.changeStudentDormitoryNoByStudentNo(studentNo[j*4+3],String.valueOf(dormitoryNoList.get(j)));
        }

        String sql1 = "update dormitory set number = ? where dormitoryNo = "+anotherDormitoryNo;
        update(sql1,String.valueOf(num2));
        for (int k = 0 ; k < num2 ; k++){
            DBStudentInfo.changeStudentDormitoryNoByStudentNo(studentNo[j*4+k],String.valueOf(anotherDormitoryNo));
        }

        return true;
    }

    private void updateDormitory(String dormitoryNo){
        String sql = "update dormitory set number = 4 where dormitoryNo = ?";

        update(sql,dormitoryNo);
    }

    private void update(String sql, String par){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,par);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(null,preparedStatement,null,connection);
        }
    }
}
