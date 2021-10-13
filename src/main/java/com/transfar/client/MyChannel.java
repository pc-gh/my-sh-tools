package com.transfar.client;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.transfar.utils.ConfigFileInputUtils;
import com.transfar.utils.SessionUtils;
import com.transfar.vo.Result;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zb
 * @version 1.0
 * @date 2021/10/11 16:44
 * @desc TODO
 */
public class MyChannel {




    public Result MyJschExec(String command) throws JSchException, IOException {

        Result result = new Result();
        String res = null;
        try{
            SessionUtils Mysession = new SessionUtils();
            ConfigFileInputUtils configFileInputUtils = new ConfigFileInputUtils();
            Properties fileInputStream = configFileInputUtils.getFileInputStream();
            String user = fileInputStream.getProperty("USER");
            String password = fileInputStream.getProperty("PASSWORD");
            String host = fileInputStream.getProperty("HOST");

            Mysession.setSession(user,host,password);
            com.jcraft.jsch.Session session = Mysession.getSession();
            session.connect();

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);

            // X Forwarding
            // channel.setXForwarding(true);

            //channel.setInputStream(System.in);
            channel.setInputStream(null);

            //channel.setOutputStream(System.out);

            //FileOutputStream fos=new FileOutputStream("/tmp/stderr");
            //((ChannelExec)channel).setErrStream(fos);
            ((ChannelExec)channel).setErrStream(System.err);
            InputStream in=channel.getInputStream();
            channel.connect();

            byte[] tmp=new byte[1024];
            int ipCount = 0;
            int ipOut = 0;
            while(true){
//                System.out.println("###in.available###"+in.available());
//                if (ipOut<4){
//                    ipOut++;
//                }else {
//                    break;
//
//                }

                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    res = new String(tmp, 0, i);
//                    res += res;

//                    System.err.print(res);
//                    if (ipCount<5){
////                        System.out.println("ipCount###"+ipCount);
//                        ipCount++;
//                    }else {
//                        break;
//                    }
                }
//                System.out.println("################");
                if(channel.isClosed()){
                    if(in.available()>0) continue;
//                    System.out.println("exit-status: "+channel.getExitStatus());
                    result.setCode(channel.getExitStatus());
                    break;
                }
//                if (ipCount>4){
//                    break;
//                }

                try{Thread.sleep(1000);}catch(Exception ee){}
            }

//            System.out.println(res);

            result.setResult(res);
            channel.disconnect();
            session.disconnect();


        }catch(Exception e){
            System.out.println(e);
        }finally {

            return result;
        }
    }
}
