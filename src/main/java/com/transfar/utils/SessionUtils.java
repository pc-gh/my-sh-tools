package com.transfar.utils;

import com.jcraft.jsch.*;

import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * @author zb
 * @version 1.0
 * @date 2021/10/11 13:18
 * @desc TODO
 */
public class SessionUtils {


    com.jcraft.jsch.Session session;

    public com.jcraft.jsch.Session getSession(){
        return session;
    }

    public com.jcraft.jsch.Session setSession(String userName, String host, String password) throws JSchException, FileNotFoundException {

        JSch jSch = new JSch();
        ConfigFileInputUtils configFileInputUtils = new ConfigFileInputUtils();
        Properties fileInputStream = configFileInputUtils.getFileInputStream();
        String sshport = fileInputStream.getProperty("SSHPORT");
        if(sshport==null){
            sshport = "22";
        }
        int sshPort = Integer.parseInt(sshport);
        session = jSch.getSession(userName,host,sshPort);
        MyUserInfoUtils myUserInfo = new MyUserInfoUtils();
        session.setPassword(password);
        session.setUserInfo(myUserInfo);
        return session;

    }


}
