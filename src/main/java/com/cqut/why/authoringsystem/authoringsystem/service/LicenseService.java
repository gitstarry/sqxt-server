package com.cqut.why.authoringsystem.authoringsystem.service;

import com.cqut.why.authoringsystem.authoringsystem.entity.dto.LicenseChangeDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.LicenseInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.LicenseParams;

import java.text.ParseException;
import java.util.List;

public interface LicenseService {
    Integer getLicenseCount(LicenseParams licenseParams);

    List<LicenseInfoDTO> getLicenseLog(LicenseParams licenseParams);

    boolean addLicense(LicenseChangeDTO licenseChangeDTO) throws ParseException;
}
