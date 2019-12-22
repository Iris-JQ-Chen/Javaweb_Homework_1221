package dao;

import bean.dormitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBDormitory {

    /*
    查询所有宿舍的所有相关信息
     */
    public static final List<dormitory> queryDorimtoryAll(){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<dormitory> dormitoryList = new LinkedList<>();
        String sql = "select * from Dormitory";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                dormitory dormitory = new dormitory();
                dormitory.setDormitoryNo(resultSet.getString("dormitoryNo"));
                dormitory.setBuildingNo(resultSet.getString("buildingNo"));
                dormitoryList.add(dormitory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        return dormitoryList;
    }

    /*
    通过宿舍号查询单个宿舍的楼编号
     */
    public static final String queryBuildingNoByDormitoryNo(String dormitoryNo){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from Dormitory where dormitoryNo = ?";
        String buildingNo = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,dormitoryNo);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                buildingNo = resultSet.getString("buildingNo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        return buildingNo;
    }

}
