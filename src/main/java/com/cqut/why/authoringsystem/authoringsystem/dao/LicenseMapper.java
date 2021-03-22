package com.cqut.why.authoringsystem.authoringsystem.dao;

import com.cqut.why.authoringsystem.authoringsystem.entity.*;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.LicenseInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.LicenseParams;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LicenseMapper {
    Integer selectLicenseCount(LicenseParams licenseParams);

    List<LicenseInfoDTO> selectLicensePage(LicenseParams licenseParams);

    int checkProgram(String programName);

    int checkEquipment(String equipmentName);

    void insertLicense(License license);


    int checkLicense(String equipmentName, String projectName,String sn);

    int insertLicenseDetail(LicenseDetail licenseDetail);

    int checkProgramVersion(int programId,String latestVersion);

    void insertProgramVersion(EquipmentProgramVersion equipmentProgramVersion);

    int checkProgramUpgradeLog(int equipmentId);

    int checkProgramVersionOrder(int programId);


    ProgramVersion checkProgramVersion1(int programId, String latestVersion);

    Equipment checkEquipment1(String equipmentName);

    Program checkProgram1(String programName);

    int checkProject(String projectName);

    ProjectInfo checkProject1(String projectName);

    void insertLicenseMigrationDetail(LicenseMigrationDetail licenseMigrationDetail);
}
