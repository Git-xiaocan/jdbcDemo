package com.xiaocan.jdbc.util;

import sun.jvm.hotspot.tools.SysPropsDumper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
            //System.out.println(properties.getProperty("jdbc.password"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 连接数据库
     * @param url
     * @param user
     * @param password
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

}
