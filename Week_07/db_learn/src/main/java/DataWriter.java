import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.time.StopWatch;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author songjiyang
 */
public class DataWriter {

    static HikariDataSource ds;

    public static void prepareConnection() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/mydb?characterEncoding=UTF-8");
        config.setUsername("root");
        config.setPassword("root");
        config.setMaximumPoolSize(20);

        ds = new HikariDataSource(config);

    }

    void defaultCreateOrder() throws SQLException {
        // 191s
        Random random = new Random();
        Connection connection = ds.getConnection();
        try {
            String orderSql = "insert into orders values(?,?,?,?,?,?,?)";
            Date now = new Date();
            PreparedStatement preparedStatement = connection.prepareStatement(orderSql);
            connection.setAutoCommit(false);
            for (int i = 1; i <= 100_000; i++) {
                preparedStatement.setString(1, String.valueOf(i));
                preparedStatement.setDate(2, new java.sql.Date(now.getTime()));
                preparedStatement.setDate(3, new java.sql.Date(now.getTime()));
                preparedStatement.setString(4, "created");
                preparedStatement.setString(5, "fixed address");
                preparedStatement.setBigDecimal(6, BigDecimal.valueOf(random.nextDouble()));
                preparedStatement.setString(7, String.valueOf(random.nextInt(100)));
                preparedStatement.execute();
                if (i % 10000 == 0) {
                    System.out.println(new Date().toString() + "defaultCreateOrder 插入10000条");
                }
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }

    }

    void batchCreateOrder() throws SQLException {
        // 92s
        Random random = new Random();
        Connection connection = ds.getConnection();
        try {
            String orderSql = "insert into orders values(?,?,?,?,?,?,?)";
            Date now = new Date();
            PreparedStatement preparedStatement = connection.prepareStatement(orderSql);
            connection.setAutoCommit(false);
            for (int i = 1; i <= 100_000; i++) {
                preparedStatement.setString(1, String.valueOf(i));
                preparedStatement.setDate(2, new java.sql.Date(now.getTime()));
                preparedStatement.setDate(3, new java.sql.Date(now.getTime()));
                preparedStatement.setString(4, "created");
                preparedStatement.setString(5, "fixed address");
                preparedStatement.setBigDecimal(6, BigDecimal.valueOf(random.nextDouble()));
                preparedStatement.setString(7, String.valueOf(random.nextInt(100)));
                preparedStatement.addBatch();
                if (i % 1000 == 0) {
                    preparedStatement.executeBatch();
                    System.out.println(new Date().toString() + "defaultCreateOrder 插入1000条");
                }
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }

    }

    public static void main(String[] args) throws SQLException {
        prepareConnection();
        StopWatch stopWatch = StopWatch.createStarted();
        new DataWriter().batchCreateOrder();
        stopWatch.stop();
        System.out.println(stopWatch.getTime(TimeUnit.SECONDS));
    }
}
