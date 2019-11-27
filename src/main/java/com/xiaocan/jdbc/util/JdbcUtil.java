package com.xiaocan.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
    private static Connection con = null;
    private static Statement stam = null;

    /**
     * 连接数据库
     * @param url
     * @param user
     * @param password
     * @return
     */
    public static Connection JdbcConnect(String url,String user,String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection(url,user,password);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
