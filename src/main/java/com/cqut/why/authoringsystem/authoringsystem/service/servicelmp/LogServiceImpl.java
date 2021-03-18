package com.cqut.why.authoringsystem.authoringsystem.service.servicelmp;

import com.cqut.why.authoringsystem.authoringsystem.dao.LogMapper;
import com.cqut.why.authoringsystem.authoringsystem.entity.EquipmentConnectLog;
import com.cqut.why.authoringsystem.authoringsystem.entity.License;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramUpgradeLog;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.EquipmentConnectLogDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentConnectParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.LicenseParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProgramUpgradeParams;
import com.cqut.why.authoringsystem.authoringsystem.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public Integer getCount(ProgramUpgradeParams programUpgradeParams) {
        return logMapper.selectCount(programUpgradeParams);
    }

    @Override
    public List<ProgramUpgradeLog> getProgramUpgradeLog(ProgramUpgradeParams programUpgradeParams) {
        return logMapper.selectPage(programUpgradeParams);
    }

    @Override
    public Integer getConnectCount(EquipmentConnectParams equipmentConnectParams) {
        return logMapper.selectConnectCount(equipmentConnectParams);
    }

    @Override
    public List<EquipmentConnectLog> getEquipmentConnectLog(EquipmentConnectParams equipmentConnectParams) {
        return logMapper.selectConnectPage(equipmentConnectParams);
    }

    @Override
    public Integer getLicenseCount(LicenseParams licenseParams) {
        return logMapper.selectLicenseCount(licenseParams);
    }

    @Override
    public List<License> getLicenseLog(LicenseParams licenseParams) {
        return logMapper.selectLicensePage(licenseParams);
    }
}
