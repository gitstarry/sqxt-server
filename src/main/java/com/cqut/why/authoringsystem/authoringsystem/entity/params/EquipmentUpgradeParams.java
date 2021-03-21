package com.cqut.why.authoringsystem.authoringsystem.entity.params;

public class EquipmentUpgradeParams extends PageableRequestDTO {
    private Integer versionId;

    private String projectName;

    private String equipmentName;

    private Integer upgradeStatus;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Integer getUpgradeStatus() {
        return upgradeStatus;
    }

    public void setUpgradeStatus(Integer upgradeStatus) {
        this.upgradeStatus = upgradeStatus;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }
}
