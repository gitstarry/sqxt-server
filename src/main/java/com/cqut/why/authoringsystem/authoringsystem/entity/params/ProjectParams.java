package com.cqut.why.authoringsystem.authoringsystem.entity.params;

public class ProjectParams extends PageableRequestDTO {
    private Integer category;
    private String version;
    private Integer status;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
