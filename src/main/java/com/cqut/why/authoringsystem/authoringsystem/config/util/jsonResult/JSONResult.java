package com.cqut.why.authoringsystem.authoringsystem.config.util.jsonResult;

import com.cqut.why.authoringsystem.authoringsystem.config.util.message.Message;
import com.cqut.why.authoringsystem.authoringsystem.config.util.page.Pageable;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JSONResult<T> implements Pageable {

    private T data;
    private Integer code;
    private Object message;
    private Integer page;
    private Integer pageSize;
    private Integer totalCount;


    public JSONResult() {
    }

    public JSONResult(Object message) {
        this.message = message;
    }


    public JSONResult(T data, Object message){
        this.data = data;
        this.message = message;
    }
    public JSONResult(T data, Object message, Pageable pageInfo, Integer totalCount){
        this(data, message);
        this.page = pageInfo.getPage();
        this.pageSize = pageInfo.getPageSize();
        this.totalCount = totalCount;
    }

    public T getData() {
        return data;
    }

    public JSONResult setData(T data) {
        this.data = data;
        return this;
    }

    public Object getMessage() {
        return message;
    }

    @Override
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessage(Message message) {
        this.message = message.toString();
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
