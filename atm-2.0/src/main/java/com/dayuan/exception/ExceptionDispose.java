package com.dayuan.exception;

import com.dayuan.dto.ResponseDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class ExceptionDispose {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResponseDTO exceptionDispose(BizException e){
        e.printStackTrace();
        return ResponseDTO.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseDTO exceptionDispose(Exception e){
        e.printStackTrace();
        return ResponseDTO.fail("系统异常");
    }
}
