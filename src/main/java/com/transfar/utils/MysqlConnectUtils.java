package com.transfar.utils;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Properties;


/**
 * @author zb
 * @version 1.0
 * @date 2021/10/11 17:35
 * @desc TODO
 */
public class MysqlConnectUtils {

    //1、私有化的静态成员变量，在静态代码块中实现赋值
    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            //2、加载驱动，同时赋值
            ConfigFileInputUtils configFileInputUtils = new ConfigFileInputUtils();
            Properties fileInputStream = configFileInputUtils.getFileInputStream();

            url = fileInputStream.getProperty("MYSQL_URL");
            user = fileInputStream.getProperty("MYSQL_USER");
            password = fileInputStream.getProperty("MYSQL_PASSWORD");
            Class.forName(fileInputStream.getProperty("MYSQL_DRIVER"));
        } catch (ClassNotFoundException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取连接，参数已经在静态代码块中被初始化，返回类型为Connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //关闭资源，此处为了提高效率，加了非空判断，如果是空，代表没获取到，则不需要关
    public static void close(Connection conn, Statement stat, ResultSet rs) {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stat != null)
                stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
