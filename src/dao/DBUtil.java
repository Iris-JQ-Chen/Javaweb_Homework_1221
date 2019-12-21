package dao;

import java.sql.*;

public class DBUtil {

    private static final String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=DormitoryManagement";
    private static final String userName = "sa";
    private static final String userPassword = "123456";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driverName);
            System.out.println("驱动加载成功");

            connection = DriverManager.getConnection(dbURL,userName,userPassword);
            System.out.println("数据库连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("驱动加载失败");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }

        return connection;
    }

    public static Boolean closeAll(ResultSet resultSet, PreparedStatement preparedStatement, Statement statement, Connection connection){
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
