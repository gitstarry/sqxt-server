package com.cqut.why.authoringsystem.authoringsystem.controller;

import com.cqut.why.authoringsystem.authoringsystem.config.util.BeanMapper.BeanMapper;
import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import com.cqut.why.authoringsystem.authoringsystem.config.util.jsonResult.JSONResult;
import com.cqut.why.authoringsystem.authoringsystem.config.util.message.Message;
import com.cqut.why.authoringsystem.authoringsystem.entity.EquipmentProgramVersion;
import com.cqut.why.authoringsystem.authoringsystem.entity.License;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramVersion;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.*;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentUpgradeParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.LicenseParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectParams;
import com.cqut.why.authoringsystem.authoringsystem.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Api(tags = "工程人员")
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @ApiOperation(value = "分页模糊查询测试结果信息")
    @PostMapping("/getProject")
    public JSONResult<List<ProjectDTO>> getProject(@RequestBody ProjectParams projectParams) {
        // 用户总数
        Integer connectCount = projectService.getProjectCount(projectParams);
        List<ProgramVersion> sysLog = projectService.getProjectLog(projectParams);
        List<ProjectDTO> logListDTOS = BeanMapper.mapList(sysLog, ProjectDTO.class);

        JSONResult<List<ProjectDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(logListDTOS);
        jsonResult.setTotalCount(connectCount);
        return jsonResult;
    }

    @ApiOperation(value = "修改测试信息")
    @PostMapping("/modifyProject")
    public JSONResult modifyProject(@RequestBody ProjectInfoDTO projectInfoDTO) {
        JSONResult jsonResult = new JSONResult();
        boolean updated = projectService.modifyUser(projectInfoDTO);
        if (updated) {
            jsonResult.setMessage(new Message("DB.UPDATE_SUCCESS", "测试结果"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.UPDATE_FAILED", "测试结果"));
        }
    }

    @ApiOperation(value = "分页模糊查询测试结果信息")
    @PostMapping("/getEquipmentUpgrade")
    public JSONResult<List<EquipmentUpgradeDTO>> getEquipmentUpgrade(@RequestBody EquipmentUpgradeParams equipmentUpgradeParams) {
        // 用户总数
        Integer connectCount = projectService.getEquipmentCount(equipmentUpgradeParams);
        List<EquipmentUpgradeDTO> sysLog = projectService.getEquipmentLog(equipmentUpgradeParams);
        List<EquipmentUpgradeDTO> logListDTOS = BeanMapper.mapList(sysLog, EquipmentUpgradeDTO.class);

        JSONResult<List<EquipmentUpgradeDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(logListDTOS);
        jsonResult.setTotalCount(connectCount);
        return jsonResult;
    }


    @ApiOperation(value = "修改设备升级信息")
    @PostMapping("/modifyEquipment")
    public JSONResult modifyEquipment(@RequestBody EquipmentUpgradeStatusDTO equipmentUpgradeStatusDTO) {
        JSONResult jsonResult = new JSONResult();
        boolean updated = projectService.modifyEquipment(equipmentUpgradeStatusDTO);
        if (updated) {
            jsonResult.setMessage(new Message("DB.UPDATE_SUCCESS", "升级"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.UPDATE_FAILED", "升级"));
        }
    }

}
