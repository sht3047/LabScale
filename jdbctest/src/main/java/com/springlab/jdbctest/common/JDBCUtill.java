package com.springlab.jdbctest.common;

import java.sql.*;

public class JDBCUtill {
    /* DB 연결 */
    public static Connection getConnection(){
        Connection conn = null;
//        System.out.println("[Magpie]>> Try DB Connection");
        try {
            /* DB */
            Class.forName("org.h2.Driver");
//            System.out.println("[Magpie]>> DB name = org.h2.Driver");
            /* url */
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
//            System.out.println("[Magpie]>> getConnection = jdbc:h2:tcp://localhost/~/test");
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(PreparedStatement stmt, Connection conn){
        if (stmt != null){
            try {
                if (!stmt.isClosed()) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                /* Java는 객체를 제거할 수단이 없기에 null로 초기화 [메모리 효율] */
                stmt = null;
            }
        }
        if (conn != null){
            try {
                if (!conn.isClosed()) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                /* Java는 객체를 제거할 수단이 없기에 null로 초기화 [메모리 효율] */
                conn = null;
            }
        }
    }
    public static void close(ResultSet resultSet, Connection conn){
        if (resultSet != null){
            try {
                if (!resultSet.isClosed()) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                /* Java는 객체를 제거할 수단이 없기에 null로 초기화 [메모리 효율] */
                resultSet = null;
            }
        }
        if (conn != null){
            try {
                if (!conn.isClosed()) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                /* Java는 객체를 제거할 수단이 없기에 null로 초기화 [메모리 효율] */
                conn = null;
            }
        }
    }
}
