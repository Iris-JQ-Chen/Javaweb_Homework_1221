package dao;

import bean.dormitoryManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBDormitoryManager {

    public static final Boolean changeBuildingNoByManagerNo(String managerNo, String buildingNo){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update dormitoryManager set buildingNo = ? where managerNo = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, buildingNo);
            preparedStatement.setString(2,managerNo);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null,preparedStatement,null,connection);
        }

        return true;

    }

    public static final Boolean changeManagerTelByManagerNo(String managerNo, String managerTel){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update dormitoryManager set managerTel = ? where mangerNo = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,managerTel);
            preparedStatement.setString(2,managerNo);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null,preparedStatement,null,connection);
        }

        return true;
    }

    public static final List<dormitoryManager> queryDormitoryManagerAll(){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<dormitoryManager> dormitoryManagerList = new LinkedList<>();
        String sql = "select * from dormitoryManager";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                dormitoryManager dormitoryManager = new dormitoryManager();
                dormitoryManager.setManagerNo(resultSet.getString("managerNo"));
                dormitoryManager.setManagerName(resultSet.getString("managerName"));
                dormitoryManager.setManagerTel(resultSet.getString("managerTel"));
                dormitoryManager.setBuildingNo(resultSet.getString("buildingNo"));
                dormitoryManagerList.add(dormitoryManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }
        return dormitoryManagerList;
    }

    public static final dormitoryManager queryDormitoryManagerByManagerNo(String  managerNo){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        dormitoryManager dormitoryManager = new dormitoryManager();
        String sql = "select * from dormitoryManager where managerNo = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,managerNo);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                dormitoryManager.setManagerNo(resultSet.getString("managerNo"));
                dormitoryManager.setManagerName(resultSet.getString("managerName"));
                dormitoryManager.setManagerTel(resultSet.getString("managerTel"));
                dormitoryManager.setBuildingNo(resultSet.getString("buildingNo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        return dormitoryManager;
    }

}
