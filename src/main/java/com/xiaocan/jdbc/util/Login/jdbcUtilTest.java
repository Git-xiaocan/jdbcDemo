package com.xiaocan.jdbc.util.Login;

import com.mysql.cj.protocol.Resultset;
import com.xiaocan.jdbc.util.JdbcUtil;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class jdbcUtilTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String user = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();
//        String sql= "create table user(\n" +
//                "id int PRIMARY key auto_increment,\n" +
//                "user VARCHAR(20),\n" +
//                "password VARCHAR(32)\n" +
//                ")engine=innodb default charset=utf8; ";
//        String[] sqlbatch = {
//                "insert into user(user,password) values('刘德华','123123')",
//                "insert into user(user,password) values('张学友','123321')",
//                "insert into user(user,password) values('狗蛋','11222')",
//        };
        Connection connection = JdbcUtil.GetConnection();
        try {
            Statement statement = connection.createStatement();
//            for (String tempsql : sqlbatch){
//                statement.addBatch(tempsql);
//        }
//            int[] i  = statement.executeBatch();
//            System.out.println(Arrays.toString(i));
            //有安全漏洞
           // String sql = String.format("select * from user where user='%s'and password='%s';",user,password);
        //这种方式安全一些
         String sql = "select * from user where user=?and password=?;";

            //ResultSet rs = statement.executeQuery(sql);
            //使用预编译模式
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){

                System.out.println("登录成功");
            }else {
                System.out.println("登录失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
