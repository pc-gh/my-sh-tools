package com.transfar.vo;

/**
 * @author zb
 * @version 1.0
 * @date 2021/10/11 17:18
 * @desc TODO
 */
public class Result {
    @Override
    public String toString() {
        return "Result{" +
                "result='" + result + '\'' +
                ", code=" + code +
                '}';
    }

    public String result = null;

    public Result() {
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int code = -1;


}
