package dao;

import bean.building;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBBuilding {

    /*
    查询所有宿舍楼的NO
     */
    public static final List<Integer> queryBuildingNo (){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Integer> integerList = new LinkedList<>();
        String sql = "select * from Building";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                integerList.add(new Integer(resultSet.getString("buildingNo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        return integerList;
    }

    /*
    查询所有宿舍楼的所有信息
     */
    public static final List<building> queryBuildingAll(){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<building> list = new LinkedList<>();
        String sql = "select * from Building";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            building building = new building();
            while (resultSet.next()){
                building.setBuildingNo(resultSet.getString("buildingNo"));
                building.setBuildingFloor(resultSet.getString("buildingFloor"));
                building.setDormitoryCount(resultSet.getString("dormitoryCount"));
                list.add(building);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        return list;
    }

    /*
    根据楼的NO查询单个宿舍楼的所有信息
     */
    public static final building queryBuilding(String buildingNo){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        building building = new building();
        String sql = "select * from Building where buildingNo = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,buildingNo);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                building.setBuildingNo(resultSet.getString("buildingNo"));
                building.setBuildingFloor(resultSet.getString("buildingFloor"));
                building.setDormitoryCount(resultSet.getString("dormitoryCount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        return building;
    }

}
