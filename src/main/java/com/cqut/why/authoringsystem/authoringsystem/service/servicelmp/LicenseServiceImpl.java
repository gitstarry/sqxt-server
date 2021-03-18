package com.cqut.why.authoringsystem.authoringsystem.service.servicelmp;

import com.cqut.why.authoringsystem.authoringsystem.config.util.BeanMapper.BeanMapper;
import com.cqut.why.authoringsystem.authoringsystem.dao.LicenseMapper;
import com.cqut.why.authoringsystem.authoringsystem.entity.EquipmentProgramVersion;
import com.cqut.why.authoringsystem.authoringsystem.entity.License;
import com.cqut.why.authoringsystem.authoringsystem.entity.LicenseDetail;
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
        int programId = licenseMapper.checkProgram(licenseChangeDTO.getProgramName());
        int equipmentId = licenseMapper.checkEquipment(licenseChangeDTO.getEquipmentName());
        String[] s = licenseChangeDTO.getGrantedAt().split("~");
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
        System.out.println(licenseChangeDTO.getGrantedAt());
        Date grantAt = sdf.parse(s[0]);
        Date expiredAt = sdf.parse(s[1]);
        License license = BeanMapper.map(licenseChangeDTO, License.class);
        license.setEquipmentId(equipmentId);
        license.setProgramId(programId);
        licenseMapper.insertLicense(license);

        int programVersionId = licenseMapper.checkProgramVersion(programId);
        int orderId = licenseMapper.checkProgramVersionOrder(programId);
        int upgrade_user_id = licenseMapper.checkProgramUpgradeLog(equipmentId);
        Date upgrade_at = licenseMapper.checkUpgrade(equipmentId);
        EquipmentProgramVersion equipmentProgramVersion = BeanMapper.map(licenseChangeDTO,EquipmentProgramVersion.class);
        equipmentProgramVersion.setProgramVersion(licenseChangeDTO.getLatestVersion());
        equipmentProgramVersion.setProgramVersionId(programVersionId);
        equipmentProgramVersion.setEquipmentId(equipmentId);
        equipmentProgramVersion.setProgramId(programId);
        equipmentProgramVersion.setUpgradeAt(upgrade_at);
        equipmentProgramVersion.setUpgradeUserId(upgrade_user_id);
        equipmentProgramVersion.setProgramVersionOrder(orderId);
        licenseMapper.insertProgramVersion(equipmentProgramVersion);


        int licenseId = licenseMapper.checkLicense(licenseChangeDTO.getEquipmentName(),licenseChangeDTO.getProjectName());
        LicenseDetail licenseDetail = BeanMapper.map(licenseChangeDTO,LicenseDetail.class);
        licenseDetail.setLicenseId(licenseId);
        licenseDetail.setGrantAt(grantAt);
        licenseDetail.setExpiredAt(expiredAt);
        licenseDetail.setProgramId(programId);
        return licenseMapper.insertLicenseDetail(licenseDetail) == 1;
    }
}
