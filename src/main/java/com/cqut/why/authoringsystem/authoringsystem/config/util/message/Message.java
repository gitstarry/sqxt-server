package com.cqut.why.authoringsystem.authoringsystem.config.util.message;

public class Message {
    private String key;
    private Object[] entity;

    public Message(String key, String... entity) {
        this.key = key;
        this.entity = entity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object[] getEntity() {
        return entity;
    }

    public void setEntity(Object[] entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return MessageUtil.getMessage(key, entity);
    }

}
