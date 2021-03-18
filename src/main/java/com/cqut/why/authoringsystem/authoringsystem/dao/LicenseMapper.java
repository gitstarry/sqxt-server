package com.cqut.why.authoringsystem.authoringsystem.dao;

import com.cqut.why.authoringsystem.authoringsystem.entity.EquipmentProgramVersion;
import com.cqut.why.authoringsystem.authoringsystem.entity.License;
import com.cqut.why.authoringsystem.authoringsystem.entity.LicenseDetail;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.LicenseChangeDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.LicenseInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.LicenseParams;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface LicenseMapper {
    Integer selectLicenseCount(LicenseParams licenseParams);

    List<LicenseInfoDTO> selectLicensePage(LicenseParams licenseParams);

    int checkProgram(String programName);

    int checkEquipment(String equipmentName);

    void insertLicense(License license);


    int checkLicense(String equipmentName, String projectName);

    int insertLicenseDetail(LicenseDetail licenseDetail);

    int checkProgramVersion(int programId);

    void insertProgramVersion(EquipmentProgramVersion equipmentProgramVersion);

    int checkProgramUpgradeLog(int equipmentId);

    Date checkUpgrade(int equipmentId);

    int checkProgramVersionOrder(int programId);
}
