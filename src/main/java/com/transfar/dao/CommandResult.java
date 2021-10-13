package com.transfar.dao;

/**
 * @author zb
 * @version 1.0
 * @date 2021/10/12 10:18
 * @desc TODO
 */
public class CommandResult {
    public CommandResult() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserCommandId() {
        return userCommandId;
    }

    public void setUserCommandId(int userCommandId) {
        this.userCommandId = userCommandId;
    }

    public String getUpdateTime() {
        return update_time;
    }

    public void setUpdateTime(String update_time) {
        this.update_time = update_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    int id;

    public CommandResult(int id, int userCommandId, String update_time, int status, String remark) {
        this.id = id;
        this.userCommandId = userCommandId;
        this.update_time = update_time;
        this.status = status;
        this.remark = remark;
    }

    int userCommandId;
    String update_time;

    @Override
    public String toString() {
        return "CommandResult{" +
                "id=" + id +
                ", userCommandId=" + userCommandId +
                ", update_time='" + update_time + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }

    int status;
    String remark;

}
