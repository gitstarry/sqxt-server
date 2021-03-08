package com.cqut.why.authoringsystem.authoringsystem.entity;

import lombok.Data;

@Data
public class Permission {
    private Integer id;

    private String name;

    private String url;

    private String tag;

    public Permission(Integer id, String name, String url, String tag) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
