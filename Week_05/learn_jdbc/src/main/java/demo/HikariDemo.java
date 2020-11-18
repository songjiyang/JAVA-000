package demo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariDemo {

    public static void main(String[] args) throws SQLException {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8");
        config.setUsername("root");
        config.setPassword("root");

        HikariDataSource ds = new HikariDataSource(config);

        Connection connection = ds.getConnection();


        BasicJdbc basicJdbc = new BasicJdbc();
        basicJdbc.insertStudent(connection);
        basicJdbc.updateStudent(connection);
        basicJdbc.findStudent(connection);
        basicJdbc.deleteStudent(connection);
    }
}
