package com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler;

import com.cqut.why.authoringsystem.authoringsystem.config.util.jsonResult.JSONResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JSONResult handleValidationException(MethodArgumentNotValidException e) {
        return new JSONResult(parseFieldErrors(e.getBindingResult().getFieldErrors()));
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JSONResult handleValidationException(BindException e) {
        return new JSONResult(parseFieldErrors(e.getBindingResult().getFieldErrors()));
    }

    private List<Map<String, String>> parseFieldErrors(List<FieldError> fieldErrors) {
        List<Map<String, String>> errorList = null;
        if (fieldErrors != null && fieldErrors.size() > 0) {
            errorList = fieldErrors.stream().map(e -> {
                Map<String, String> tmp = new HashMap<>();
                tmp.put("field", e.getField());
                tmp.put("code", e.getCode());
                tmp.put("message", e.getDefaultMessage());
                return tmp;
            }).collect(Collectors.toList());
        }
        return errorList;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JSONResult handleBusinessException(BusinessException e) {
        JSONResult result = new JSONResult();
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public JSONResult handleAccessDeniedException(AccessDeniedException e){
        JSONResult result = new JSONResult();
        result.setMessage("该角色无权访问");
        return result;
    }
}
