package com.transfar.client;

import com.jcraft.jsch.JSchException;
import com.transfar.dao.CommandResult;
import com.transfar.dao.UserCommand;
import com.transfar.vo.Result;

import java.io.IOException;

/**
 * @author zb
 * @version 1.0
 * @date 2021/10/12 10:14
 * @desc TODO
 */
public class UserTool {

    Result result = new Result();

    public void commandINit(){


    }

    public Result userPing(UserCommand userCommand) throws IOException, JSchException {

        String command = userCommand.getCommand();
        String host = userCommand.gethost();

        String commandhost = command + " -c 4 " + host;

        MyChannel myChannel = new MyChannel();

//        Result resultCount = myChannel.MyJschExec("ps -ef | grep 'systemd' | wc -l");
//        System.out.println("###userPing###commandhost###"+commandhost);
        result = myChannel.MyJschExec(commandhost);

//        System.out.println(result.getResult());


        return result;
    }


    public Result userPs(UserCommand userCommand) throws IOException, JSchException {
        MyChannel myChannel = new MyChannel();

        //ps -ef | grep 'systemd' | wc -l
        String commandPs = userCommand.getCommand()+" -ef | grep"+" "+userCommand.getItem()+" | wc -l";
        Result result = myChannel.MyJschExec(commandPs);
        String[] split = result.getResult().split("\n");
        result.setResult(String.valueOf(Integer.parseInt(split[0])-1));
//        System.out.println(result.toString());
        return result;
    }

    public Result userTelnet(UserCommand userCommand) throws IOException, JSchException {

        // echo quit | timeout --signal=9 2 telnet baidu.com 80
        String command = "echo quit | timeout --signal=9 2 "+userCommand.getCommand()+" "+userCommand.gethost()+" "+userCommand.getPort();

        MyChannel myChannel = new MyChannel();
        Result result = myChannel.MyJschExec(command);
        return result;
    }

    public Result userWeb(UserCommand userCommand) throws IOException, JSchException {
        // curl -I -m 10 -o /dev/null -s -w %{http_code} www.baidu.com
        String command = userCommand.getCommand()+" -I -m 10 -o /dev/null -s -w %{http_code} "+userCommand.getUrl();
        MyChannel myChannel = new MyChannel();
//        System.out.println(command);
        Result result = myChannel.MyJschExec(command);

//        System.out.println(result.toString());
        return result;
    }


}
