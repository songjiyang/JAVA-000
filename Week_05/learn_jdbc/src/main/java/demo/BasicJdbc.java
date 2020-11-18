package demo;

import java.sql.*;

public class BasicJdbc {
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

    public void insertStudent(Connection connection) throws SQLException {
        String sql = "insert into student values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "student1");
        preparedStatement.execute();

    }
    public void updateStudent(Connection connection) throws SQLException {
        String sql = "update student set name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "updatedStudent1");
        preparedStatement.execute();

    }

    public void findStudent(Connection connection) throws SQLException {
        String sql = "select * from student where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("name"));

        }

    }

    public void deleteStudent(Connection connection) throws SQLException {
        String sql = "delete from student where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
        preparedStatement.execute();


    }

    public static void main(String[] args) throws SQLException {
        BasicJdbc l = new BasicJdbc();
        Connection connection = l.getConnection();
//        l.insertStudent(connection);
//        l.updateStudent(connection);
//        l.findStudent(connection);
        l.deleteStudent(connection);
        l.findStudent(connection);
    }
}
