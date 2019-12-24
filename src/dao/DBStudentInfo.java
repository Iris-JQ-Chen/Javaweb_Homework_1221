package dao;

import bean.student;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBStudentInfo {

    public static final Boolean changeStudentInfoByStudentNo(String studentNo, int instituteNo, String dormitoryNo, String studentTel){
        String sql = "update StudentInfo set instituteNo = '"+instituteNo+"', dormitoryNo = '"+dormitoryNo+"', studentTel = '"+studentTel+"' where studentNo = '"+studentNo+"'";
        return change(sql);
    }

    /*
    查询所有学生的信息
     */
    public static final List<student> queryStudentAll(){
        String sql = "select * from studentInfo";
        return query(sql);
    }

    /*
    根据学生编号查询学生的所有信息
     */
    public static final student queryStudentByStudentNo(String studentNo){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        student student = new student();
        String sql = "select * from studentInfo where studentNo = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,studentNo);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                student.setStudentNo(resultSet.getString("studentNo"));
                student.setInstituteNo(resultSet.getByte("instituteNo"));
                student.setDormitoryNo(resultSet.getString("dormitoryNo"));
                student.setStudentName(resultSet.getString("studentName"));
                student.setStudentSex(resultSet.getString("studentSex"));
                student.setStudentGrade(resultSet.getString("studentGrade"));
                student.setStudentTel(resultSet.getString("studentTel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        return student;
    }

    /*
    根据学院编号NO查询这个学院的所有学生信息
     */
    public static final List<student> queryStudentByinstituteNo(int instituteNo){
        String sql = "select * from studentInfo where instituteNo = '"+instituteNo+"'";
        return query(sql);
    }

    /*
    根据宿舍编号NO查询这个宿舍的所有学生信息
     */
    public static final List<student> queryStudentByDormitoryNo(String dormitoryNo){
        String sql = "select * from studentInfo where dormitoryNo = '"+dormitoryNo+"'";
        return query(sql);
    }

    /*
    根据学生姓名查询到这个学生的所有信息
     */
    public static final List<student> queryStudentByStudentName(String studentName){
        String sql = "select * from studentInfo where studentName = '"+studentName+"'";
        return query(sql);
    }

    /*
    根据学生性别查询所有学社信息
     */
    public static final List<student> queryStudentByStudentSex(String studentSex){
        String sql = "select * from studentInfo where studentSex = '"+studentSex+"'";
        return query(sql);
    }

    private static final Boolean change(String sql){
        Connection connection = DBUtil.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null,null,statement,connection);
        }
        return true;
    }

    private static final List<student> query(String sql){
        Connection connection = DBUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<student> studentList = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                student student = new student();
                student.setStudentNo(resultSet.getString("studentNo"));
                student.setInstituteNo(resultSet.getByte("instituteNo"));
                student.setDormitoryNo(resultSet.getString("dormitoryNo"));
                student.setStudentName(resultSet.getString("studentName"));
                student.setStudentSex(resultSet.getString("studentSex"));
                student.setStudentGrade(resultSet.getString("studentGrade"));
                student.setStudentTel(resultSet.getString("studentTel"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,null,statement,connection);
        }

        return studentList;
    }
}
