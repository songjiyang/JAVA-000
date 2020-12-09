package com.songjiyang.distributed_db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;


/**
 * @author songjiyang
 */
public class CrudExample {

    static HikariDataSource ds;

    public static void prepareConnection() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/sharding_db?characterEncoding=UTF-8");
        config.setUsername("root");
        config.setPassword("root");
        config.setMaximumPoolSize(10);

        ds = new HikariDataSource(config);

    }


    void defaultCreateOrder() throws SQLException {
        // 191s
        Random random = new Random();
        Connection connection = ds.getConnection();
        try {
            String orderSql = "insert into orders values(?,?,?,?,?,?,?)";
            String orderItemSql = "insert into order_item values(?,?,?,?,?,?,?)";

            Date now = new Date();
            PreparedStatement preparedStatement = connection.prepareStatement(orderSql);

            PreparedStatement itemPreparedStatement = connection.prepareStatement(orderItemSql);

            connection.setAutoCommit(false);
            for (int i = 1; i <= 1000; i++) {
                int userId = random.nextInt(100000);
                preparedStatement.setInt(1, i);
                preparedStatement.setDate(2, new java.sql.Date(now.getTime()));
                preparedStatement.setDate(3, new java.sql.Date(now.getTime()));
                preparedStatement.setString(4, "created");
                preparedStatement.setString(5, "fixed address");
                preparedStatement.setBigDecimal(6, BigDecimal.valueOf(random.nextDouble()));
                preparedStatement.setInt(7, userId);
                System.out.println(preparedStatement);
                preparedStatement.execute();

                itemPreparedStatement.setString(1, String.valueOf(i));
                itemPreparedStatement.setInt(2, i);
                itemPreparedStatement.setBigDecimal(3, BigDecimal.valueOf(random.nextDouble()));
                itemPreparedStatement.setDate(4, new java.sql.Date(now.getTime()));
                itemPreparedStatement.setDate(5, new java.sql.Date(now.getTime()));
                itemPreparedStatement.setString(6, "product id");
                itemPreparedStatement.setInt(7, userId);
                System.out.println(itemPreparedStatement);

                itemPreparedStatement.execute();
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }

    }

    public void findOrder() throws SQLException {
        String sql = "select * from orders o left join order_item i on o.id=i.order_id where o.id=?";
        Connection connection = ds.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 230);
        ResultSet resultSet = preparedStatement.executeQuery();
        int columnsNumber = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = resultSet.getString(i);
                System.out.println(resultSet.getMetaData().getColumnName(i) + ": " + columnValue);
            }
            System.out.println("");
        }

    }

    public void updateOrder() throws SQLException {
        Connection connection = ds.getConnection();
        String sql = "update orders set update_time=? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1, new java.sql.Date(new Date().getTime()));
        preparedStatement.setInt(2, 50);

        preparedStatement.execute();

    }

    public void deleteOrder() throws SQLException {
        Connection connection = ds.getConnection();
        String sql = "delete from orders where id=?";
        String itemSql = "delete from order_item where order_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 100);
        preparedStatement.execute();


        preparedStatement = connection.prepareStatement(itemSql);
        preparedStatement.setInt(1, 100);
        preparedStatement.execute();

    }


    public static void main(String[] args) throws SQLException {
        prepareConnection();
//        new CrudExample().defaultCreateOrder();
//        new CrudExample().findOrder();
//        new CrudExample().deleteOrder();
        new CrudExample().updateOrder();
    }
}
