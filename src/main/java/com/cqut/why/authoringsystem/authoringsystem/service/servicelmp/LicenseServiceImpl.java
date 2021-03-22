package com.cqut.why.authoringsystem.authoringsystem.service.servicelmp;

import com.cqut.why.authoringsystem.authoringsystem.config.util.BeanMapper.BeanMapper;
import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import com.cqut.why.authoringsystem.authoringsystem.dao.LicenseMapper;
import com.cqut.why.authoringsystem.authoringsystem.entity.*;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.LicenseChangeDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.LicenseInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.LicenseParams;
import com.cqut.why.authoringsystem.authoringsystem.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LicenseServiceImpl implements LicenseService {
    @Autowired
    LicenseMapper licenseMapper;

    @Override
    public Integer getLicenseCount(LicenseParams licenseParams) {
        return licenseMapper.selectLicenseCount(licenseParams);
    }

    @Override
    public List<LicenseInfoDTO> getLicenseLog(LicenseParams licenseParams) {
        return licenseMapper.selectLicensePage(licenseParams);
    }

    @Override
    public boolean addLicense(LicenseChangeDTO licenseChangeDTO) throws ParseException {
        Program program = licenseMapper.checkProgram1(licenseChangeDTO.getProgramName());
        if(program == null) {
            throw new BusinessException("该算法不存在");
        }
        int programId = licenseMapper.checkProgram(licenseChangeDTO.getProgramName());
        Equipment equipment = licenseMapper.checkEquipment1(licenseChangeDTO.getEquipmentName());
        if(equipment == null) {
            throw new BusinessException("该设备不存在");
        }
        ProjectInfo project = licenseMapper.checkProject1(licenseChangeDTO.getProjectName());
        if(project == null) {
            throw new BusinessException("该项目不存在");
        }
        int projectId = licenseMapper.checkProject(licenseChangeDTO.getProjectName());
        int equipmentId = licenseMapper.checkEquipment(licenseChangeDTO.getEquipmentName());
        String[] s = licenseChangeDTO.getGrantedAt().split("~");
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
        System.out.println(licenseChangeDTO.getGrantedAt());
        Date grantAt = sdf.parse(s[0]);
        Date expiredAt = sdf.parse(s[1]);
        License license = BeanMapper.map(licenseChangeDTO, License.class);
        license.setEquipmentId(equipmentId);
        license.setProgramId(programId);
        license.setSn(String.valueOf(Math.random()*100000 +10000));
        licenseMapper.insertLicense(license);

        ProgramVersion programVersion = licenseMapper.checkProgramVersion1(programId,licenseChangeDTO.getLatestVersion());
        if(programVersion == null) {
            throw new BusinessException("该版本不存在");
        }
        int programVersionId = licenseMapper.checkProgramVersion(programId,licenseChangeDTO.getLatestVersion());
        int orderId = licenseMapper.checkProgramVersionOrder(programId);
        EquipmentProgramVersion equipmentProgramVersion = BeanMapper.map(licenseChangeDTO,EquipmentProgramVersion.class);
        equipmentProgramVersion.setProgramVersion(licenseChangeDTO.getLatestVersion());
        equipmentProgramVersion.setProgramVersionId(programVersionId);
        equipmentProgramVersion.setEquipmentId(equipmentId);
        equipmentProgramVersion.setProgramId(programId);
        equipmentProgramVersion.setProgramVersionOrder(orderId);
        licenseMapper.insertProgramVersion(equipmentProgramVersion);

        LicenseMigrationDetail licenseMigrationDetail = BeanMapper.map(licenseChangeDTO,LicenseMigrationDetail.class);
        licenseMigrationDetail.setEquipmentId(equipmentId);
        licenseMigrationDetail.setProjectId(projectId);
        licenseMigrationDetail.setEquipmentModel(equipment.getModel());
        licenseMigrationDetail.setGrantAt(grantAt);
        licenseMigrationDetail.setActivateAt(grantAt);
        licenseMigrationDetail.setReceiveUserName(equipment.getReceiveUserName());
        licenseMapper.insertLicenseMigrationDetail(licenseMigrationDetail);

        int licenseId = licenseMapper.checkLicense(licenseChangeDTO.getEquipmentName(),licenseChangeDTO.getProjectName(),license.getSn());
        LicenseDetail licenseDetail = BeanMapper.map(licenseChangeDTO,LicenseDetail.class);
        licenseDetail.setLicenseId(licenseId);
        licenseDetail.setGrantAt(grantAt);
        licenseDetail.setExpiredAt(expiredAt);
        licenseDetail.setProgramId(programId);
        return licenseMapper.insertLicenseDetail(licenseDetail) == 1;
    }
}
