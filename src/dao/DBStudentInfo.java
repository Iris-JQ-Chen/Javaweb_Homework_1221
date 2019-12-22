package dao;

import bean.student;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBStudentInfo {

    /*
    根据学生编号NO更改该学生学院信息
     */
    public static final Boolean changeInstituteNoByStudentNo(String studentNo, int instituteNo){
        String sql = "update studentInfo set instituteNo = '"+instituteNo+"' where studentNo = '"+studentNo+"'";
        return change(sql);
    }

    /*
    根据学生编号NO更改学生宿舍信息
     */
    public static final Boolean changeDormitoryNoByStudentNo(String studentNo, String dormitoryNo){
        String sql = "update studentInfo set dormitoryNo = '"+dormitoryNo+"' where studentNo = '"+studentNo+"'";
        return change(sql);
    }

    /*
    根据学生编号NO更改学生电话
     */
    public static final Boolean changeStudentTelByStudentNo(String studentNo,String studentTel){
        String sql = "update studentInfo set studentTel = '"+studentTel+"' where studentNo = '"+studentNo+"'";
        return change(sql);
    }

    /*
    查询所有学生的信息
     */
    public static final List<student> queryStudentAll(){
//        Connection connection = DBUtil.getConnection();
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        List<student> studentList = new LinkedList<>();
//        String sql = "select * from student";
//
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                student student = new student();
//                student.setStudentNo(resultSet.getString("studentNo"));
//                student.setInstituteNo(resultSet.getByte("instituteNo"));
//                student.setDormitoryNo(resultSet.getString("dormitoryNo"));
//                student.setStudentName(resultSet.getString("studentName"));
//                student.setStudentSex(resultSet.getString("studentSex"));
//                student.setStudentGrade(resultSet.getString("studentGrade"));
//                student.setStudentTel(resultSet.getString("studentTel"));
//                studentList.add(student);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
//        }
//
//        return studentList;

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
            return statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null,null,statement,connection);
        }
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
