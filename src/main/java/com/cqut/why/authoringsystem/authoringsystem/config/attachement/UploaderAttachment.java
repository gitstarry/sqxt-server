package com.cqut.why.authoringsystem.authoringsystem.config.attachement;

public class UploaderAttachment {
    private String name;
    private String objName;
    private String contentType;
    private String  extension;
    private String  innerUrl;
    private String  url;
    private Long length;

    public UploaderAttachment(String name, String objName, String contentType, String extension, String innerUrl, String url, Long length) {
        this.name = name;
        this.objName = objName;
        this.contentType = contentType;
        this.extension = extension;
        this.innerUrl = innerUrl;
        this.url = url;
        this.length = length;
    }

    public UploaderAttachment(String name, String contentType, String extension, String innerUrl, String url, Long length) {
        this.name = name;
        this.contentType = contentType;
        this.extension = extension;
        this.innerUrl = innerUrl;
        this.url = url;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getInnerUrl() {
        return innerUrl;
    }

    public void setInnerUrl(String innerUrl) {
        this.innerUrl = innerUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
