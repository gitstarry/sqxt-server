package com.cqut.why.authoringsystem.authoringsystem.service;

import com.cqut.why.authoringsystem.authoringsystem.entity.EquipmentConnectLog;
import com.cqut.why.authoringsystem.authoringsystem.entity.License;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramUpgradeLog;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentConnectParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.LicenseParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProgramUpgradeParams;

import java.util.List;

public interface LogService {
    Integer getCount(ProgramUpgradeParams programUpgradeParams);

    List<ProgramUpgradeLog> getProgramUpgradeLog(ProgramUpgradeParams programUpgradeParams);

    Integer getConnectCount(EquipmentConnectParams equipmentConnectParams);

    List<EquipmentConnectLog> getEquipmentConnectLog(EquipmentConnectParams equipmentConnectParams);

    Integer getLicenseCount(LicenseParams licenseParams);

    List<License> getLicenseLog(LicenseParams licenseParams);
}
