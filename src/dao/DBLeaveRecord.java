package dao;

import bean.leaveRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBLeaveRecord {

    /*
    根据学生编号NO查询这个学生的所有离校记录
     */
    public static final List<leaveRecord> queryLeaveRecordByStudentNo(String studentNo){
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<leaveRecord> leaveRecordList = new LinkedList<>();
        String sql = "select * from leaveRecord where studentNo = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,studentNo);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                leaveRecord leaveRecord = new leaveRecord();
                leaveRecord.setLeaveId(resultSet.getByte("leaveId"));
                leaveRecord.setStudentNo(resultSet.getString("studentNo"));
                leaveRecord.setPlace(resultSet.getString("place"));
                leaveRecord.setReason(resultSet.getString("reason"));
                leaveRecord.setLeaveDate(resultSet.getDate("leaveDate"));
                leaveRecord.setExbackDate(resultSet.getDate("exbackDate"));
                leaveRecord.setAcbackDate(resultSet.getDate("acbackDate"));
                leaveRecordList.add(leaveRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(resultSet,preparedStatement,null,connection);
        }
        return leaveRecordList;
    }

}
