package com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler;

import com.cqut.why.authoringsystem.authoringsystem.config.util.message.Message;

public class BusinessException extends RuntimeException{

    public BusinessException(String message){
        super(message);
    }
    public BusinessException(Message message){
        super(message.toString());
    }
}
