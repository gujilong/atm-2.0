package com.dayuan.dto;



public class ResponseDTO   {
    private int code = 1000;
    private String message;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseDTO() {

    }

    public ResponseDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static ResponseDTO success() {
        return new ResponseDTO(1000, "");
    }

    public static ResponseDTO success(Object data) {
        ResponseDTO responseDTO = new ResponseDTO(1000, "");
        responseDTO.setData(data);
        return responseDTO;
    }

    public static ResponseDTO fail(String errorMessage) {
        return new ResponseDTO(9999, errorMessage);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
