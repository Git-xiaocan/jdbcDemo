package com.xiaocan.jdbc.util;



import com.mysql.cj.protocol.Resultset;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    private static Properties properties = null;
    private static final String DatabaseConfig = "dbConfig/DataBaseConfig.properties";
    private static Connection con = null;
    private static Statement stam = null;
    static {
        properties = new Properties();
        InputStream instream = Thread.currentThread().getContextClassLoader().getResourceAsStream(DatabaseConfig);
        try {
            properties.load(instream);
            //System.out.println(properties.getProperty("jdbc.url"));
          Class.forName(properties.getProperty("jdbc.driver"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 连接数据库
     * @return
     */
    public static Connection GetConnection(){
        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.user");
        String password = properties.getProperty("jdbc.password");

        try {

            return  DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭资源方法
     * @param con
     * @param sta
     * @param res
     */
    public static void Close(Connection con, Statement sta, ResultSet res){
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (sta !=null) {
                try {
                    sta.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }




    }

    /**
     * 更新数据库方法
     * @param sql
     * @return
     */
    public static int executeUpdate(String sql){
    Connection con = null;
    Statement sta = null;
        try {
            con = GetConnection();
            sta = con.createStatement();
            return sta.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     *封装执行sql批处理方法
     */
    public static int [] executeBatch(String [] sql){
        Connection con = GetConnection();
        Statement sta = null;
        try {
            sta  = con.createStatement();
            //添加批处理语句
           for(String tempSql:sql){
            sta.addBatch(tempSql);
           }
           return sta.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                sta.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    public static Resultset executeQuery(String sql){
        Connection con = GetConnection();
        Statement sta = null;




        try {
            sta = con.createStatement();

            return (Resultset) sta.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
