package com.transfar.utils;

import java.io.*;
import java.util.Properties;

/**
 * @author zb
 * @version 1.0
 * @date 2021/10/12 9:28
 * @desc TODO
 */
public class ConfigFileInputUtils {


    public Properties getFileInputStream() throws FileNotFoundException {
        Properties properties = new Properties();
//        InputStream inputStream = Object.class.getResourceAsStream("/config.properties");
//        InputStream inputStream = Object.class.getResourceAsStream("env.properties");
        File file = new File("env.properties");
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
//            properties.load(inputStream);
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;


    }






}
