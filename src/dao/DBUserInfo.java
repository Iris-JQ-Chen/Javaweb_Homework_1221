package dao;

import bean.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserInfo {

    /*
    用户实现登录
     */
    public static Boolean userLogin(String userId, String userPassword, String limit){
        String sql = "select * from UserInfo where userId = ? and password = ? and limit = ?";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userId);
            preparedStatement.setString(2,userPassword);
            preparedStatement.setString(3,limit);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }
    }

    /*
    通过用户id和limit查询用户密码
     */
    public static user queryUserInfo(String userId, String limit){
        String sql = "select * from UserInfo where userId = ? and limit = ?";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        user user = new user();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userId);
            preparedStatement.setString(2,limit);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user.setUserId(resultSet.getString("userId"));
                user.setUserPasswrod(resultSet.getString("password"));
                user.setLimit(resultSet.getString("limit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        return user;
    }

    /*
    用户修改密码
     */
    public static Boolean changeUserPassword(String userId, String password, String limit){
        String sql = "update UserInfo set password = ? where userId = ? and limit = ?";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,userId);
            preparedStatement.setString(3,limit);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null,preparedStatement,null,connection);
        }
        return true;
    }

}
