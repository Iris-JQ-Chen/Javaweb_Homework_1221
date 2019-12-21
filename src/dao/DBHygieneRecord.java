package dao;

import bean.hygieneRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBHygieneRecord {

    public static final List<hygieneRecord> queryByDormitoryNo(String dormitoryNo){
        String sql = "select * from hygieneRecord where dormitoryNo = ?";
        return queryByPara(sql,dormitoryNo);
    }

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
