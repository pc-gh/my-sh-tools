package com.transfar.utils;

import com.transfar.dao.CommandResult;
import com.transfar.dao.UserCommand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zb
 * @version 1.0
 * @date 2021/10/12 19:53
 * @desc TODO
 */
public class DbCRUDUtils {


    public List<UserCommand> SelectCommand(String sqlD) throws SQLException {

        //取得连接对象
        MysqlConnectUtils mysqlConnectUtils = new MysqlConnectUtils();
        Connection conn = mysqlConnectUtils.getConnection();
        //准备sql语句
        String sql = sqlD;
        //取得预编译对象
        PreparedStatement pst = conn.prepareStatement(sql);
        //执行sql语句
        ResultSet rs = pst.executeQuery();
//        System.out.println("###sql:"+sql+"###rs:"+rs.next());
        ArrayList<UserCommand> userCommandList = new ArrayList<UserCommand>();
        //判断返回值是否存在，并取出返回值
        while (rs.next()) {
            int id = rs.getInt(1);
            int typeCode = rs.getInt(2);
            String command = rs.getString(3);
            String url = rs.getString(4);
            int port = rs.getInt(5);
            int threshold = rs.getInt(6);

            String host = rs.getString(7);
            String item = rs.getString(8);
            String remark = rs.getString(9);

            UserCommand userCommand = new UserCommand(item,host,id,typeCode,command,url,port,threshold,remark);
//            System.out.println(userCommand.toString());
            userCommandList.add(userCommand);

        }
        //关闭连接对象
        MysqlConnectUtils.close(conn,pst,rs);
        return userCommandList;


    }


    public CommandResult selectCommandResult(String sqlD) throws SQLException {

        //取得连接对象
        MysqlConnectUtils mysqlConnectUtils = new MysqlConnectUtils();
        Connection conn = mysqlConnectUtils.getConnection();
        //准备sql语句
        String sql = sqlD;
        //取得预编译对象
        PreparedStatement pst = conn.prepareStatement(sql);
        //执行sql语句
        ResultSet rs = pst.executeQuery();
//        System.out.println("###sql:"+sql+"###rs:"+rs.next());
        //判断返回值是否存在，并取出返回值
//        System.out.println("sql:"+sql+"###查询结果："+rs.next());
        CommandResult commandResult = new CommandResult();

        if (rs.next()) {
            int id = rs.getInt(1);
            int userCommandId = rs.getInt(2);
            String updateTime = rs.getString(3);
            int status = rs.getInt(4);
            String remark = rs.getString(5);

            commandResult.setId(id);
            commandResult.setUpdateTime(updateTime);
            commandResult.setUserCommandId(userCommandId);
            commandResult.setStatus(status);
            commandResult.setRemark(remark);
//            System.out.println(userCommand.toString());

        }else {
            commandResult = null;
        }
        //关闭连接对象
        MysqlConnectUtils.close(conn,pst,rs);
        return commandResult;

    }


    public int insertResult(String sqlD,CommandResult commandResult) throws SQLException {
        //取得连接对象
        MysqlConnectUtils mysqlConnectUtils = new MysqlConnectUtils();
        Connection conn = mysqlConnectUtils.getConnection();
        //准备sql语句
        String sql = sqlD;
        //取得预编译对象
        // insert into command_result where user_command_id=? and update_time=? and status=?
        PreparedStatement pst = conn.prepareStatement(sql);
        //执行sql语句

//        pst.setInt(1,commandResult.getId());
        pst.setInt(1,commandResult.getUserCommandId());
        pst.setString(2,commandResult.getUpdateTime());
        pst.setInt(3,commandResult.getStatus());
//        pst.setString(5,commandResult.getRemark());
        int count = pst.executeUpdate();
        System.out.println(pst.toString());

        pst.close();
        conn.close();
        return count;

    }






}
