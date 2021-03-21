package com.cqut.why.authoringsystem.authoringsystem.entity.params;

public class ProgramParams extends PageableRequestDTO  {
    private String name;

    private String latestVersion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }
}
