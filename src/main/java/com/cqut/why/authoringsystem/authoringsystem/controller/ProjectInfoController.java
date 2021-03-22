package com.cqut.why.authoringsystem.authoringsystem.controller;


import com.cqut.why.authoringsystem.authoringsystem.config.util.BeanMapper.BeanMapper;
import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import com.cqut.why.authoringsystem.authoringsystem.config.util.jsonResult.JSONResult;
import com.cqut.why.authoringsystem.authoringsystem.config.util.message.Message;
import com.cqut.why.authoringsystem.authoringsystem.entity.Equipment;
import com.cqut.why.authoringsystem.authoringsystem.entity.LicenseMigrationDetail;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.*;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentAddParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectAllParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectInfoParams;
import com.cqut.why.authoringsystem.authoringsystem.service.ProjectInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@Api(tags = "行政")
@RequestMapping("/projectInfo")
public class ProjectInfoController {
    @Autowired
    ProjectInfoService projectInfoService;

    @ApiOperation(value = "模糊查询所有项目信息")
    @PostMapping("/getAllProjectInfo")
    public JSONResult<List<ProjectInfoDTO>> getProgramUpgradeLog(@RequestBody ProjectAllParams projectAllParams) {
        // 用户总数
        Integer count = projectInfoService.getCount(projectAllParams);
        List<ProjectInfoDTO> sysLog = projectInfoService.getProjectLog(projectAllParams);
        for(int i = 0; i < sysLog.size(); i++) {
            ProjectInfoDTO projectInfoDTO = sysLog.get(i);
            int id = projectInfoService.selectProjectId(sysLog.get(i).getName());
            Boolean check = projectInfoService.checkProject(id);
            projectInfoDTO.setCheck(check);
            sysLog.set(i,projectInfoDTO);
        }
        List<ProjectInfoDTO> logListDTOS = BeanMapper.mapList(sysLog, ProjectInfoDTO.class);

        JSONResult<List<ProjectInfoDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(logListDTOS);
        jsonResult.setTotalCount(count);
        return jsonResult;
    }

    @ApiOperation(value = "新增项目及客户")
    @PostMapping("/newProjectInfo")
    public JSONResult newProjectInfo(@RequestBody ProjectInfoParams projectInfoParams) {
        JSONResult<ProjectInfoParams> jsonResult = new JSONResult<>();
        boolean flag = projectInfoService.addProjectInfo(projectInfoParams);
        if (flag) {
            jsonResult.setMessage(new Message("DB.ADD_SUCCESS", "项目"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.ADD_FAILED", "项目"));
        }
    }

    @ApiOperation(value = "删除项目")
    @GetMapping("/deleteProject")
    public JSONResult getAllEquipmentInfo(Integer id) {
        JSONResult<ProjectInfoParams> jsonResult = new JSONResult<>();
        boolean flag = projectInfoService.deleteProject(id);
        if (flag) {
            jsonResult.setMessage(new Message("DB.DELETE_SUCCESS", "项目"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.DELETE_FAILED", "项目"));
        }
    }

    @ApiOperation(value = "删除设备")
    @GetMapping("/deleteEquipment")
    public JSONResult deleteEquipment(Integer id) {
        JSONResult<ProjectInfoParams> jsonResult = new JSONResult<>();
        boolean flag = projectInfoService.deleteEquipment(id);
        if (flag) {
            jsonResult.setMessage(new Message("DB.DELETE_SUCCESS", "设备"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.DELETE_FAILED", "设备"));
        }
    }

    @ApiOperation(value = "根据id获取项目信息")
    @GetMapping("/getProjectInfo")
    public JSONResult<ProjectInfoParams> getProjectInfo(Integer id) {
        JSONResult<ProjectInfoParams> jsonResult = new JSONResult<>();
        ProjectInfoParams user = projectInfoService.getById(id);
        ProjectInfoParams userInfoDTO = new ProjectInfoParams();
        BeanUtils.copyProperties(user, userInfoDTO);
        jsonResult.setData(userInfoDTO);
        return jsonResult;
    }

    @ApiOperation(value = "编辑项目及客户")
    @PostMapping("/editProjectInfo")
    public JSONResult editProjectInfo(@RequestBody ProjectInfoParams projectInfoParams) {
        JSONResult jsonResult = new JSONResult();
        boolean updated = projectInfoService.updateProjectInfo(projectInfoParams);
        if (updated) {
            jsonResult.setMessage(new Message("DB.UPDATE_SUCCESS", "项目信息"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.UPDATE_FAILED", "项目信息"));
        }
    }

    @ApiOperation(value = "项目信息-查询设备详情")
    @GetMapping("/selectEquipmentInfo")
    public JSONResult<List<EquipmentInfoDTO>> selectEquipmentInfo(Integer id) {
        List<LicenseMigrationDetail> sysLog = projectInfoService.selectAllEquipmentInfo(id);
        List<EquipmentInfoDTO> logListDTOS = BeanMapper.mapList(sysLog, EquipmentInfoDTO.class);
        JSONResult<List<EquipmentInfoDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(logListDTOS);
        return jsonResult;
    }

    @ApiOperation(value = "新增设备")
    @PostMapping("/addEquipment")
    public JSONResult addEquipment(@RequestBody EquipmentAddParams equipmentAddParams) {
        JSONResult<ProjectInfoParams> jsonResult = new JSONResult<>();
        boolean flag = projectInfoService.addEquipment(equipmentAddParams);
        if (flag) {
            jsonResult.setMessage(new Message("DB.ADD_SUCCESS", "设备"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.ADD_FAILED", "设备"));
        }
    }

    @ApiOperation(value = "设备模糊查询")
    @PostMapping("/getEquipmentLike")
    public JSONResult<List<EquipmentDetailDTO>> getEquipmentLike(@RequestBody EquipmentAddParams equipmentAddParams) {
        // 用户总数
        Integer count = projectInfoService.getEquipmentCount(equipmentAddParams);
        List<Equipment> sysLog = projectInfoService.getEquipmentLog(equipmentAddParams);
        for(int i = 0; i < sysLog.size(); i++) {
            Equipment equipment = sysLog.get(i);
            int id = projectInfoService.selectEquipmentId(sysLog.get(i).getName());
            Boolean check = projectInfoService.checkEquipment(id);
            equipment.setCheck(check);
            sysLog.set(i,equipment);
        }
        List<EquipmentDetailDTO> logListDTOS = BeanMapper.mapList(sysLog, EquipmentDetailDTO.class);

        JSONResult<List<EquipmentDetailDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(logListDTOS);
        jsonResult.setTotalCount(count);
        return jsonResult;
    }

    @ApiOperation(value = "设备相信信息")
    @GetMapping("/getEquipmentDetailInfo")
    public JSONResult<EquipmentDetailInfoDTO> getEquipmentDetailInfo(Integer id) {

        EquipmentDetailInfoDTO equipment = projectInfoService.getEquipmentDetailInfo(id);
        JSONResult<EquipmentDetailInfoDTO> jsonResult = new JSONResult<>();
        jsonResult.setData(equipment);
        return jsonResult;
    }
}
