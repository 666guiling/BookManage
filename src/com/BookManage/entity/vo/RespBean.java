package com.BookManage.entity.vo;

/*
@ClassName : RespBean
@Author : 不会吧
@Date: 2022/9/20 14:59
@Description : 
*/
public class RespBean {
    private int status;
    private String msg;
    private Object data;

    public RespBean() {
    }

    public RespBean(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    //响应成功,携带提示语句和数据
    public static RespBean respSuccess(String msg,Object data){
        return new RespBean(200,msg,data);
    }
    //响应成功,携带提示语句
    public static RespBean respSuccess(String msg){
        return new RespBean(200,msg,null);
    }
    //响应成功,携带数据
    public static RespBean respSuccess(Object data){
        return new RespBean(200,null,data);
    }
    //响应失败,携带提示语句和数据
    public static RespBean respError(String msg,Object data){
        return new RespBean(500,msg,data);
    }
    //响应失败,携带提示语句
    public static RespBean respError(String msg){
        return new RespBean(500,msg,null);
    }
    //响应失败,携带数据
    public static RespBean respError(Object data){
        return new RespBean(500,null,data);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
