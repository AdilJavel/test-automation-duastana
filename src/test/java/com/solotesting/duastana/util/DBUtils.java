package com.solotesting.duastana.util;



import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBUtils {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void createConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String dbUrl = "jdbc:mysql://localhost:3306/testingdb?autoReconnect=true&useSSL=false";
        String dbUsername = "root";
        String dbPassword = "password";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();      }


    }

    private static void executeQuery(String query) {
        try {
            statement = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        try {
            resultSet = (ResultSet) statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getColumnNames(String query) {
        executeQuery(query);
        List<String> columns = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = (ResultSetMetaData) resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    public static List<Map<String, Object>> getQueryResultMap(String query) {
        executeQuery(query);
        List<Map<String, Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = (ResultSetMetaData) resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowList;
    }

    public static List<List<Object>> getQueryResultList(String query) {
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = (ResultSetMetaData) resultSet.getMetaData();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                rowList.add(row);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return rowList;
    }



    public static String getColumnValue(String query, String column) {
        executeQuery(query);
        List<Object> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = (ResultSetMetaData) resultSet.getMetaData();
            while (resultSet.next()) {
                rowList.add(resultSet.getObject(column));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowList.toString().substring(1,rowList.toString().length()-1);
    }

    public static void destroy() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}


