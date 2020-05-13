package com.jdbc.day1;

import java.sql.*;

public class BasicTest {

    public static void main(String[] args) throws SQLException {
        String URL = "jdbc:oracle:thin:@54.198.155.113:1521:xe";
        String username = "hr";
        String password = "hr";
        //to establish connection with database
        Connection connection = DriverManager.getConnection(URL, username, password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        while(resultSet.next()){
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
        }

        resultSet.beforeFirst(); //to come back to the beginning of result set

        //some technical information about database
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        //some technical information about resultset
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        System.out.println("JDBC driver: " + databaseMetaData.getDriverName());
        System.out.println("JDBC driver version: " + databaseMetaData.getDriverVersion());
        System.out.println("Database name: " + databaseMetaData.getDatabaseProductName());
        System.out.println("Database version: " + databaseMetaData.getDatabaseProductVersion());

        System.out.println("Number of columns: " + resultSetMetaData.getColumnCount());
        System.out.println("Label of 1st column: " + resultSetMetaData.getColumnLabel(1));
        System.out.println("Data type of first column: " + resultSetMetaData.getColumnTypeName(1));

        System.out.println("###########################");

        //iterate rows
        while(resultSet.next()){
            //iterate columns
            for (int columnIndex = 1; columnIndex < resultSetMetaData.getColumnCount(); columnIndex++) {
                System.out.printf("%-15s", resultSet.getString(columnIndex) + " ");
            }
            System.out.println("");
        }

//        while(resultSet.next()){
//            System.out.println(resultSet.getString("salary"));
//        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
