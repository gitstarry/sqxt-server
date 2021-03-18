package com.cqut.why.authoringsystem.authoringsystem.dao;

import com.cqut.why.authoringsystem.authoringsystem.entity.EquipmentConnectLog;
import com.cqut.why.authoringsystem.authoringsystem.entity.License;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramUpgradeLog;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentConnectParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.LicenseParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProgramUpgradeParams;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogMapper {
    Integer selectCount(ProgramUpgradeParams programUpgradeParams);

    List<ProgramUpgradeLog> selectPage(ProgramUpgradeParams programUpgradeParams);

    Integer selectConnectCount(EquipmentConnectParams equipmentConnectParams);

    List<EquipmentConnectLog> selectConnectPage(EquipmentConnectParams equipmentConnectParams);

    Integer selectLicenseCount(LicenseParams licenseParams);

    List<License> selectLicensePage(LicenseParams licenseParams);
}
