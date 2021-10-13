package com.transfar.dao;

/**
 * @author zb
 * @version 1.0
 * @date 2021/10/12 10:18
 * @desc TODO
 */
public class UserCommand {

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    String item;

    public String gethost() {
        return host;
    }

    public void sethost(String host) {
        this.host = host;
    }

    String host;

    @Override
    public String toString() {

        return "UserCommand{" +
                "id=" + id +
                ", typeCode='" + typeCode + '\'' +
                ", command='" + command + '\'' +
                ", url='" + url + '\'' +
                ", port=" + port +
                ", host=" + host +
                ", item=" + item +
                ", threshold=" + threshold +
                ", remark='" + remark + '\'' +
                '}';
    }

    public UserCommand() {

    }


    public UserCommand(String item, String host, int id, int typeCode, String command, String url, int port, int threshold, String remark) {
        this.item = item;
        this.host = host;
        this.id = id;
        this.typeCode = typeCode;
        this.command = command;
        this.url = url;
        this.port = port;
        this.threshold = threshold;
        this.remark = remark;
    }

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    int typeCode;
    String command;
    String url;
    int port;
    int threshold;
    String remark;

}
