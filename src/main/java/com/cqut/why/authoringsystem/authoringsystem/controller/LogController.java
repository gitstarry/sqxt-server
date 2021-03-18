package com.cqut.why.authoringsystem.authoringsystem.controller;

import com.cqut.why.authoringsystem.authoringsystem.config.util.BeanMapper.BeanMapper;
import com.cqut.why.authoringsystem.authoringsystem.config.util.jsonResult.JSONResult;
import com.cqut.why.authoringsystem.authoringsystem.entity.EquipmentConnectLog;
import com.cqut.why.authoringsystem.authoringsystem.entity.License;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramUpgradeLog;
import com.cqut.why.authoringsystem.authoringsystem.entity.User;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.EquipmentConnectLogDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.LicenseDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProgramUpgradeLogDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.SysUserListDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentConnectParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.LicenseParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProgramUpgradeParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.SysUserQueryParams;
import com.cqut.why.authoringsystem.authoringsystem.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Api(tags = "日志")
@RequestMapping("/log")
public class LogController {
    @Autowired
    LogService logService;

    @ApiOperation(value = "分页模糊查询硬件升级记录信息")
    @PostMapping("/getProgramUpgradeLog")
    public JSONResult<List<ProgramUpgradeLogDTO>> getProgramUpgradeLog(@RequestBody ProgramUpgradeParams programUpgradeParams) {
        // 用户总数
        Integer count = logService.getCount(programUpgradeParams);
        List<ProgramUpgradeLog> sysLog = logService.getProgramUpgradeLog(programUpgradeParams);
        List<ProgramUpgradeLogDTO> logListDTOS = BeanMapper.mapList(sysLog, ProgramUpgradeLogDTO.class);

        JSONResult<List<ProgramUpgradeLogDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(logListDTOS);
        jsonResult.setTotalCount(count);
        return jsonResult;
    }

    @ApiOperation(value = "分页模糊查询设备连接记录信息")
    @PostMapping("/getEquipmentConnectLog")
    public JSONResult<List<EquipmentConnectLogDTO>> getProgramUpgradeLog(@RequestBody EquipmentConnectParams equipmentConnectParams) {
        // 用户总数
        Integer connectCount = logService.getConnectCount(equipmentConnectParams);
        List<EquipmentConnectLog> sysLog = logService.getEquipmentConnectLog(equipmentConnectParams);
        List<EquipmentConnectLogDTO> logListDTOS = BeanMapper.mapList(sysLog, EquipmentConnectLogDTO.class);

        JSONResult<List<EquipmentConnectLogDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(logListDTOS);
        jsonResult.setTotalCount(connectCount);
        return jsonResult;
    }

    @ApiOperation(value = "分页模糊查询授权变更信息")
    @PostMapping("/getLicense")
    public JSONResult<List<LicenseDTO>> getLicense(@RequestBody LicenseParams licenseParams) {
        // 用户总数
        Integer connectCount = logService.getLicenseCount(licenseParams);
        List<License> sysLog = logService.getLicenseLog(licenseParams);
        List<LicenseDTO> logListDTOS = BeanMapper.mapList(sysLog, LicenseDTO.class);

        JSONResult<List<LicenseDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(logListDTOS);
        jsonResult.setTotalCount(connectCount);
        return jsonResult;
    }

}
