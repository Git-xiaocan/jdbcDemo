package com.xiaocan.jdbc.util;

import org.junit.Test;
import sun.applet.Main;
import sun.jvm.hotspot.tools.SysPropsDumper;

import java.sql.*;
import java.util.Arrays;

import static org.junit.Assert.*;

public class JdbcUtilTest {


    public void testExecuteBatch(){
        Connection con = JdbcUtil.GetConnection();
        System.out.println(con);
        Statement statement = null;
        String [] sqlBatch= {
       "insert into student(name,class) values('狗屎','技校');",
        "insert into student(name,class) values('狗1','技校');",
        "insert into student(name,class) values('狗2','技校');",
        "insert into student(name,class) values('狗3','技校');"
        };
        int[] effectCount = null;
        try {
            statement = con.createStatement();
            for (String sql:sqlBatch){
                statement.addBatch(sql);
            }
            effectCount = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("影响的行数" + Arrays.toString(effectCount) );
    }

    public void testInsertNew() {

        Connection con = JdbcUtil.GetConnection();
        try {
            Statement statement = con.createStatement();
            String insertSql = "insert into student(name,class) values('狗屎','机电2班')";
              //int row =  statement.executeUpdate(insertSql);
            ResultSet resultSet = statement.executeQuery("select * from student;");
           // System.out.println("影响了" + row + "行");
            while (resultSet.next()){

                System.out.println(resultSet.getInt("Id") + " " + resultSet.getString("name") + " "+resultSet.getString("class"));

            }
            JdbcUtil.Close(con,statement,resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }

    }



}