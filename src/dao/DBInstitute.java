package dao;

import bean.institute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBInstitute {

    /*
    根据学院编号NO修改辅导员手机号
     */
    public static final Boolean changeCoachTel(int instituteNo, String coachTel){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update Institute set coachTel = ? where instituteNo = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,coachTel);
            preparedStatement.setInt(2,instituteNo);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null,preparedStatement,null,connection);
        }

        return true;
    }

    /*
    根据学院编号NO修改辅导员和辅导员手机号
     */
    public static final Boolean changeCoachAndCoachTel(int instituteNo, String coach, String coachTel){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update Institute set coach = ?, coachTel = ? where instituteNo = ?;";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,coach);
            preparedStatement.setString(2,coachTel);
            preparedStatement.setInt(3,instituteNo);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null,preparedStatement,null,connection);
        }

        return true;
    }

    /*
    查询所有学院的所有相关信息
     */
    public static final List<institute> queryInstituteAll(){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<institute> instituteList = new LinkedList<>();
        String sql = "select * from institute";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                institute institute = new institute();
                institute.setInstituteNo(resultSet.getByte("instituteNo"));
                institute.setInstituteName(resultSet.getString("instituteName"));
                institute.setCoach(resultSet.getString("coach"));
                institute.setCoachTel(resultSet.getString("coachTel"));
                instituteList.add(institute);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        System.out.println("数据库查询成功"+instituteList.size());
        return instituteList;
    }

    /*
    根据学院编号NO查询某个学院的所有相关信息
     */
    public static final institute queryInstituteByInstituteNo(int instituteNo){
        String sql = "select * from institute where instituteNo = ?";
        return queryInstitute(sql,instituteNo);
    }

    /*
    根据学院名查询某个学院的所有相关信息
     */
    public static final institute queryInstituteByInstituteName(String instituteName){
        String sql = "select * from institute where instituteName = ?";
        return queryInstitute(sql,instituteName);
    }

    /*
    根据导师姓名查询某个学院的所有相关信息
     */
    public static final institute queryInstituteByCoach(String coach){
        String sql = "select * from institute where coach = ?";
        return queryInstitute(sql,coach);
    }

    private static final institute queryInstitute(String sql, int para){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        institute institute = new institute();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,para);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                institute = new institute();
                institute.setInstituteNo(resultSet.getByte("instituteNo"));
                institute.setInstituteName(resultSet.getString("instituteName"));
                institute.setCoach(resultSet.getString("coach"));
                institute.setCoachTel(resultSet.getString("coachTel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        return institute;
    }

    private static final institute queryInstitute(String sql, String para){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        institute institute = new institute();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,para);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                institute = new institute();
                institute.setInstituteNo(resultSet.getByte("instituteNo"));
                institute.setInstituteName(resultSet.getString("instituteName"));
                institute.setCoach(resultSet.getString("coach"));
                institute.setCoachTel(resultSet.getString("coachTel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        return institute;
    }

}
