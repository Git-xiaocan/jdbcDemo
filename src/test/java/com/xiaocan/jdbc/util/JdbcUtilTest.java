package com.xiaocan.jdbc.util;


import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Connection;

import static org.junit.Assert.*;

public class JdbcUtilTest {


    @Test
    public void jdbcConnect() {
        String url = "jdbc:mysql://118.24.220.231:3306/testbase?serverTimezone=UTC&useSSl=false&useUnicode=true";
       String username = "root";
       String password = "lzc20017";

     Connection con = com.xiaocan.jdbc.util.JdbcUtil.JdbcConnect(url,username, password);
        if (con!=null){
            System.out.println("连接成功");
        }else {
            System.out.println("连接失败");
        }

    }
}
