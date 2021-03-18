package com.cqut.why.authoringsystem.authoringsystem.controller;

import com.cqut.why.authoringsystem.authoringsystem.config.util.BeanMapper.BeanMapper;
import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import com.cqut.why.authoringsystem.authoringsystem.config.util.jsonResult.JSONResult;
import com.cqut.why.authoringsystem.authoringsystem.config.util.message.Message;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.LicenseChangeDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.LicenseInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.LicenseParams;
import com.cqut.why.authoringsystem.authoringsystem.service.LicenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
@Api(tags = "发放许可授权")
@RequestMapping("/license")
public class LicenseController {
    @Autowired
    LicenseService licenseService;

    @ApiOperation(value = "分页模糊查询财政信息")
    @PostMapping("/getLicenseChange")
    public JSONResult<List<LicenseInfoDTO>> getLicenseChange(@RequestBody LicenseParams licenseParams) {
        // 用户总数
        Integer connectCount = licenseService.getLicenseCount(licenseParams);
        List<LicenseInfoDTO> sysLog = licenseService.getLicenseLog(licenseParams);
        List<LicenseInfoDTO> logListDTOS = BeanMapper.mapList(sysLog, LicenseInfoDTO.class);

        JSONResult<List<LicenseInfoDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(logListDTOS);
        jsonResult.setTotalCount(connectCount);
        return jsonResult;
    }

    @ApiOperation(value = "新增授权财政")
    @PostMapping("/addLicense")
    public JSONResult addLicense(@RequestBody LicenseChangeDTO licenseChangeDTO) throws ParseException {
        JSONResult jsonResult = new JSONResult();
        boolean flag = licenseService.addLicense(licenseChangeDTO);
        if (flag) {
            jsonResult.setMessage(new Message("DB.ADD_SUCCESS", "用户"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.ADD_FAILED", "用户"));
        }
    }
}
