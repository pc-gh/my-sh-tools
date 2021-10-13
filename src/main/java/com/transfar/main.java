package com.transfar;

import com.jcraft.jsch.JSchException;
import com.transfar.client.UserTool;
import com.transfar.dao.CommandResult;
import com.transfar.dao.UserCommand;
import com.transfar.utils.DbCRUDUtils;
import com.transfar.vo.Result;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author zb
 * @version 1.0
 * @date 2021/10/13 14:12
 * @desc TODO
 */
public class main {

    /*
     * @desc ping\ps\telnet\curl命令巡检工具
     * @params
     * @author zb
     * @date 2021/10/13 14:14
     * @return
     */

    public static void main(String[]args) throws SQLException, IOException, JSchException {

        DbCRUDUtils dbCRUDUtils = new DbCRUDUtils();
        UserTool userTool = new UserTool();

        String sql = "select * from user_command";
        List<UserCommand> userCommands = dbCRUDUtils.SelectCommand(sql);
//        System.out.println("###userCommands###"+userCommands.toString());
//        System.out.println(userCommands.size());
        for (int i = 0;i<userCommands.size();i++){
            UserCommand userCommand = userCommands.get(i);
            if(userCommand.getTypeCode()==1){
                Result result = userTool.userPing(userCommand);

                //通：0；找不到主机：2；不通：1；
                int code  = result.getCode();
                CommandResult commandResult = new CommandResult();
                commandResult.setUserCommandId(userCommand.getId());
                // 时间处理
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String current = df.format(System.currentTimeMillis());
//            System.out.println(current);
                commandResult.setUpdateTime(current);

                if (code==0) {
                    commandResult.setStatus(1);

                    System.out.println("主机:"+userCommand.gethost()+"可ping通！");
                }else if(code==2){
                    commandResult.setStatus(0);

                    System.out.println("找不到主机:"+userCommand.gethost()+"！");
                }else if(code==1){
                    commandResult.setStatus(0);

                    System.out.println("主机"+userCommand.gethost()+"不可达！");
                }

                CommandResult commandResultIsIn = dbCRUDUtils.selectCommandResult("select * from command_result where user_command_id=" + userCommand.getId());
                if(commandResultIsIn!=null){
                    System.out.println(userCommand.toString()+"记录"+userCommand.toString()+"已巡检过！");
                    String sqlIsIn = "update command_result set user_command_id=?,update_time=?,status=? where id="+commandResultIsIn.getId();
                    int isIn = dbCRUDUtils.insertResult(sqlIsIn, commandResult);
                    if (isIn>0){
                        System.out.println(commandResult.toString()+"记录更新入库成功");
                        System.out.println("#######################################################################");

                    }else {

                        System.out.println(commandResult.toString()+"记录更新入库失败！！！");
                        System.out.println("#######################################################################");
                    }

                }else{

                    String upSql = "insert into command_result(user_command_id,update_time,status) values(?,?,?)";
                    int res = dbCRUDUtils.insertResult(upSql, commandResult);
                    if (res>0){
                        System.out.println(commandResult.toString()+"记录入库成功");
                        System.out.println("#######################################################################");

                    }else {

                        System.out.println(commandResult.toString()+"记录入库失败！！！");
                        System.out.println("#######################################################################");
                    }
                }

            }else if(userCommand.getTypeCode()==2){

                Result result = userTool.userPs(userCommand);

                //result:1 无；其他：正常
                CommandResult commandResult = new CommandResult();
                commandResult.setUserCommandId(userCommand.getId());

                // 时间处理
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String current = df.format(System.currentTimeMillis());
//            System.out.println(current);
                commandResult.setUpdateTime(current);

                int code = Integer.parseInt(result.getResult());
                if(code<=userCommand.getThreshold()){
                    commandResult.setStatus(1);

                    System.out.println(userCommand.getItem()+"不满足告警条件！(>"+userCommand.getThreshold()+")");
                }else{
                    commandResult.setStatus(0);

                    System.out.println(userCommand.getItem()+"告警！"+"超过设定阈值"+userCommand.getThreshold());
                }
                CommandResult commandResultIsIn = dbCRUDUtils.selectCommandResult("select * from command_result where user_command_id=" + userCommand.getId());
                if(commandResultIsIn!=null){
                    System.out.println(userCommand.toString()+"记录"+userCommand.toString()+"已巡检过！");
                    String sqlIsIn = "update command_result set user_command_id=?,update_time=?,status=? where id="+commandResultIsIn.getId();
                    int isIn = dbCRUDUtils.insertResult(sqlIsIn, commandResult);
                    if (isIn>0){
                        System.out.println(commandResult.toString()+"记录更新入库成功");
                        System.out.println("#######################################################################");

                    }else {

                        System.out.println(commandResult.toString()+"记录更新入库失败！！！");
                        System.out.println("#######################################################################");
                    }


                }else {
                    String upSql = "insert into command_result(user_command_id,update_time,status) values(?,?,?)";
                    int res = dbCRUDUtils.insertResult(upSql, commandResult);
                    if (res > 0) {
                        System.out.println(commandResult.toString() + "记录入库成功");
                        System.out.println("#######################################################################");

                    } else {

                        System.out.println(commandResult.toString() + "记录入库失败！！！");
                        System.out.println("#######################################################################");
                    }
                }

            }else if(userCommand.getTypeCode()==3){

                Result result = userTool.userTelnet(userCommand);

                int code = result.getCode();
                CommandResult commandResult = new CommandResult();
                commandResult.setUserCommandId(userCommand.getId());

                // 时间处理
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String current = df.format(System.currentTimeMillis());
//            System.out.println(current);
                commandResult.setUpdateTime(current);

                if (code==1){
                    commandResult.setStatus(1);
                    System.out.println("telnet "+userCommand.gethost()+" "+userCommand.getPort()+"通！");

                }else if(code==137){
                    commandResult.setStatus(0);
                    System.out.println("telnet"+userCommand.gethost()+" "+userCommand.getPort()+"不通！");
                }else{
                    commandResult.setStatus(0);
                    System.out.println("telnet"+userCommand.gethost()+" "+userCommand.getPort()+"不通！");
                }

                CommandResult commandResultIsIn = dbCRUDUtils.selectCommandResult("select * from command_result where user_command_id=" + userCommand.getId());
                if(commandResultIsIn!=null){
                    System.out.println(userCommand.toString()+"记录"+userCommand.toString()+"已巡检过！");
                    String sqlIsIn = "update command_result set user_command_id=?,update_time=?,status=? where id="+commandResultIsIn.getId();
                    int isIn = dbCRUDUtils.insertResult(sqlIsIn, commandResult);
                    if (isIn>0){
                        System.out.println(commandResult.toString()+"记录更新入库成功");
                        System.out.println("#######################################################################");

                    }else {

                        System.out.println(commandResult.toString()+"记录更新入库失败！！！");
                        System.out.println("#######################################################################");
                    }


                }else {
                    String upSql = "insert into command_result(user_command_id,update_time,status) values(?,?,?)";
                    int res = dbCRUDUtils.insertResult(upSql, commandResult);
                    if (res > 0) {
                        System.out.println(commandResult.toString() + "记录入库成功");
                        System.out.println("#######################################################################");

                    } else {

                        System.out.println(commandResult.toString() + "记录入库成功失败！！！");
                        System.out.println("#######################################################################");
                    }
                }

            }else if(userCommand.getTypeCode()==4){

                Result result = userTool.userWeb(userCommand);
                String res = result.getResult();

                int code = result.getCode();
                CommandResult commandResult = new CommandResult();
                commandResult.setUserCommandId(userCommand.getId());

                // 时间处理
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String current = df.format(System.currentTimeMillis());
//            System.out.println(current);
                commandResult.setUpdateTime(current);

                if (res.equals("200")){
                    commandResult.setStatus(1);

                    System.out.println("网页"+userCommand.getUrl()+"可正常访问！");
                }else if(res.equals("000")){
                    commandResult.setStatus(0);

                    System.out.println("主机"+userCommand.getUrl()+"不存在或不可达！");
                }else{
                    System.err.println(res);
                    commandResult.setStatus(0);

                    System.out.println(result.getResult());

                }

                CommandResult commandResultIsIn = dbCRUDUtils.selectCommandResult("select * from command_result where user_command_id=" + userCommand.getId());
                if(commandResultIsIn!=null){
                    System.out.println(userCommand.toString()+"记录"+userCommand.toString()+"已巡检过！");
                    String sqlIsIn = "update command_result set user_command_id=?,update_time=?,status=? where id="+commandResultIsIn.getId();
                    int isIn = dbCRUDUtils.insertResult(sqlIsIn, commandResult);
                    if (isIn>0){
                        System.out.println(commandResult.toString()+"记录更新入库成功");
                        System.out.println("#######################################################################");

                    }else {

                        System.out.println(commandResult.toString()+"记录更新入库失败！！！");
                        System.out.println("#######################################################################");
                    }


                }else {
                    String upSql = "insert into command_result(user_command_id,update_time,status) values(?,?,?)";
                    int resu = dbCRUDUtils.insertResult(upSql, commandResult);
                    if (resu > 0) {
                        System.out.println(commandResult.toString() + "记录入库成功！");
                        System.out.println("#######################################################################");

                    } else {

                        System.out.println(commandResult.toString() + "记录入库失败！！！");
                        System.out.println("#######################################################################");
                    }
                }

            }else {
                System.out.println("命令类型错误！！！###例1：ping；2:ps；3:telnet；4:curl###");

            }

        }



    }
}
