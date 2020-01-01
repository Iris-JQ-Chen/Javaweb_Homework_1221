package dao;

import bean.dormitory;

import java.sql.*;
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
根据宿管员号查询这个宿管员所在的所有宿舍楼的全部宿舍
 */
    public static final List<dormitory> queryDormitoryByManagerNo(String managerNo){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<dormitory> dormitoryList = new LinkedList<>();
        String sql = "select * from Dormitory where buildingNo = (select buildingNo from DormitoryManager where managerNo = ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,managerNo);
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


    public static final Boolean retireDormitory(String studentNo, String number){
        Connection connection = DBUtil.getConnection();
        Statement statement1 = null;
        Statement statement2 = null;
        String sql1 = "update StudentInfo set dormitoryNo = null where studentNo = "+studentNo;
        String sql2 = "update Dormitory set number = "+number;

        try {
            connection.setAutoCommit(false);

            statement1 = connection.createStatement();
            statement1.execute(sql1);

            statement2 = connection.createStatement();
            statement2.execute(sql2);

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(null,null,statement1,null);
            DBUtil.closeAll(null,null,statement2,connection);
        }

        return true;
    }

    public static final int querydormitorynumberbystudentno(String studentNo){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select number from Dormitory where dormitoryNo = (select dormitoryNo from StudentInfo where studentNo = ?)";
        int number = -1;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,studentNo);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                number = new Integer(resultSet.getString("number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return number;
    }
}
