package dao;

import bean.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAdminInfo {

    public static Boolean changeAdminTel(String adminId, String adminTel){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update AdminInfo set adminTel = ? where adminId = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,adminTel);
            preparedStatement.setString(2,adminId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null, preparedStatement,null,connection);
        }

        return true;
    }

    public static admin queryAdmin(String adminId){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        admin admin = new admin();
        String sql = "select * from AdminInfo where adminId = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,adminId);
            resultSet = preparedStatement.executeQuery(sql);
            if (resultSet.next()){
                admin.setAdminId(resultSet.getString("adminId"));
                admin.setAdminName(resultSet.getString("adminName"));
                admin.setAdminTel(resultSet.getString("adminTel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        return admin;
    }

}
