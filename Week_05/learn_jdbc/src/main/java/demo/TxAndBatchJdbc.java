package demo;

import java.sql.*;

public class TxAndBatchJdbc {
    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8", "root", "root");
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void insertStudent(Connection connection, int id, String name) throws SQLException {
        String sql = "insert into student values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.execute();

    }

    public void transactionOp(Connection connection) throws SQLException {
        connection.setAutoCommit(false);

        for (int i = 1; i < 100; i++) {
            insertStudent(connection, i, "student" + i);
        }

        connection.commit();
    }

    public void batchOp(Connection connection) throws SQLException {
        String sql = "insert into student values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 1; i < 100; i++) {
            preparedStatement.setInt(1, i);
            preparedStatement.setString(2, "student" + i);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

    }

    public static void main(String[] args) throws SQLException {
        TxAndBatchJdbc l = new TxAndBatchJdbc();
        Connection connection = l.getConnection();
//        l.transactionOp(connection);
        l.batchOp(connection);
    }
}
