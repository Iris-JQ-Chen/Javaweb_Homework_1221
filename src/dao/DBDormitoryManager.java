package dao;

import bean.dormitoryManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBDormitoryManager {

    /*
    通过宿管员编号修改宿管员信息
     */
    public static final Boolean changeDormitoryManagerInfoByManagerNo(String managerNo, String buildingNo, String managerTel){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update dormitoryManager set buildingNo = ?, managerTel = ? where managerNo = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, buildingNo);
            preparedStatement.setString(2, managerTel);
            preparedStatement.setString(3,managerNo);
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
    通过学生学号查询他所在的宿舍楼的所有宿管员信息
     */
    public static List<dormitoryManager> queryDormitoryManagerByStudentNo(String studentNo){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<dormitoryManager> dormitoryManagerList = new LinkedList<>();
        String sql = "select * from DormitoryManager where buildingNo = (select buildingNo from Dormitory where dormitoryNo = (select dormitoryNo from StudentInfo where studentNo = ?))";


        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,studentNo);
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

    /*
    获得所有宿管员的所有相关信息
     */
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

    /*
    查询某个NO的宿管员的所有信息
     */
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
