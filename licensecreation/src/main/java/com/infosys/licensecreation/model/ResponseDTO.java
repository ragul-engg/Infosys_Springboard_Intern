package com.infosys.licensecreation.model;

public class ResponseDTO {
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseDTO(String msg) {
        this.msg = msg;
    }
}
