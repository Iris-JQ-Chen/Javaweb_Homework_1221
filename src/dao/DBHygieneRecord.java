package dao;

import bean.hygieneRecord;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBHygieneRecord {

    /*
    根据宿舍号NO查询某个宿舍的所有卫生记录
     */
    public static final List<hygieneRecord> queryByDormitoryNo(String dormitoryNo){
        String sql = "select * from hygieneRecord where dormitoryNo = ?";
        return queryByPara(sql,dormitoryNo);
    }

    public static final List<hygieneRecord> queryHygieneRecordByStudentNo(String studentNo){
        String sql = "select * from HygieneRecord where dormitoryNo = (select dormitoryNo from StudentInfo where studentNo = ?)";
        return queryByPara(sql,studentNo);
    }

    public static final List<hygieneRecord> queryHygieneRecordByManagerNo(String managerNo){
        String sql = "select * from HygieneRecord where managerNo = ?";
        return queryByPara(sql,managerNo);
    }

    public static final Boolean addHygieneRecord(String managerNo, String dormitoryNo, String hygieneNo, String date){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into HygieneRecord (managerNo,dormitoryNo,hygieneGrade,recordDate) values (?,?,?,?);";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,managerNo);
            preparedStatement.setString(2,dormitoryNo);
            preparedStatement.setString(3,hygieneNo);
            preparedStatement.setString(4,date);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null,preparedStatement,null,connection);
        }

        return true;
    }

    /*
    根据日期查询当天所有的卫生记录
     */
    public static final List<hygieneRecord> queryByRecordDate(String recordDate){
        String sql = "select * from hygieneRecord where recordDate = ?";
        return queryByPara(sql,recordDate);
    }

    private static final List<hygieneRecord> queryByPara(String sql, String para){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<hygieneRecord> hygieneRecordList = new LinkedList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,para);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                hygieneRecord hygieneRecord = new hygieneRecord();
                hygieneRecord.setHygieneId(resultSet.getInt("hygieneId"));
                hygieneRecord.setManagerNo(resultSet.getString("managerNo"));
                hygieneRecord.setDormitoryNo(resultSet.getString("dormitoryNo"));
                hygieneRecord.setHygieneGrade(resultSet.getString("hygieneGrade"));
                hygieneRecord.setRecordDate(resultSet.getDate("recordDate"));
                hygieneRecordList.add(hygieneRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }

        return hygieneRecordList;
    }

}
