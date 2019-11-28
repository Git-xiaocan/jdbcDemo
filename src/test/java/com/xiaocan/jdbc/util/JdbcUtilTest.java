package com.xiaocan.jdbc.util;

import org.junit.Test;
import sun.applet.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class JdbcUtilTest {

    @Test
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
            resultSet.close();
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }

    }



}