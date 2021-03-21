package com.cqut.why.authoringsystem.authoringsystem.controller;


import com.cqut.why.authoringsystem.authoringsystem.config.util.jsonResult.JSONResult;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProjectInfo;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.*;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentAddParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectInfoParams;
import com.cqut.why.authoringsystem.authoringsystem.service.ProjectInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin
@Api(tags = "行政")
@RequestMapping("/projectInfo")
public class ProjectInfoController {
    @Autowired
    ProjectInfoService projectInfoService;

    @ApiOperation(value = "查询所有项目信息")
    @GetMapping("/getAllProjectInfo")
    public JSONResult<List<ProjectInfoDTO>> getProject(HttpServletRequest request, HttpServletResponse response) {

        List<ProjectInfoDTO> prjInfo = projectInfoService.selectAllProjectInfo();

        JSONResult<List<ProjectInfoDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(prjInfo);
        return jsonResult;
    }

    @ApiOperation(value = "模糊查询信息")
    @PostMapping("/selectAllLike")
    public JSONResult<List<ProjectInfoDTO>> selectAllLike(@RequestBody ProjectInfoParams projectInfoParams) {

        System.out.println(projectInfoParams);
        List<ProjectInfoDTO> prjInfo = projectInfoService.selectAllLike(projectInfoParams);

        JSONResult<List<ProjectInfoDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(prjInfo);
        return jsonResult;
    }

    @ApiOperation(value = "新增项目及客户")
    @PostMapping("/newProjectInfo")
    public JSONResult<Boolean> newProjectInfo(@RequestBody ProjectInfoParams projectInfoParams) {

        Boolean isOk = projectInfoService.addProjectInfo(projectInfoParams);
        JSONResult<Boolean> jsonResult = new JSONResult<>();
        jsonResult.setData(isOk);
        return jsonResult;
    }

    @ApiOperation(value = "删除项目")
    @GetMapping("/deleteProject")
    public JSONResult<Boolean> getAllEquipmentInfo(Integer id) {

        Boolean isOk = projectInfoService.deleteProject(id);

        JSONResult<Boolean> jsonResult = new JSONResult<>();
        jsonResult.setData(isOk);
        return jsonResult;
    }

    @ApiOperation(value = "编辑项目及客户")
    @PostMapping("/editProjectInfo")
    public JSONResult<Integer> editProjectInfo(@RequestBody ProjectInfoParams projectInfoParams) {

        int isOk = projectInfoService.updateProjectInfo(projectInfoParams);
        System.out.println(isOk);
        JSONResult<Integer> jsonResult = new JSONResult<>();
        jsonResult.setData(isOk);
        return jsonResult;
    }

    @ApiOperation(value = "项目信息-查询设备详情")
    @GetMapping("/selectEquipmentInfo")
    public JSONResult<List<EquipmentInfoDTO>> selectEquipmentInfo(String prjName) {

        List<EquipmentInfoDTO> eqInfo = projectInfoService.selectAllEquipmentInfo(prjName);

        JSONResult<List<EquipmentInfoDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(eqInfo);
        return jsonResult;
    }

    @ApiOperation(value = "查询所有设备")
    @GetMapping("/getAllEquipmentDetail")
    public JSONResult<List<EquipmentDetailDTO>> getAllEquipmentInfo() {

        List<EquipmentDetailDTO> eqDetail = projectInfoService.selectAllEquipmentDetail();

        JSONResult<List<EquipmentDetailDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(eqDetail);
        return jsonResult;
    }

    @ApiOperation(value = "新增设备")
    @PostMapping("/addEquipment")
    public JSONResult<Boolean> addEquipment(@RequestBody EquipmentAddParams equipmentAddParams) {

        Boolean isOk = projectInfoService.addEquipment(equipmentAddParams);
        JSONResult<Boolean> jsonResult = new JSONResult<>();
        jsonResult.setData(isOk);
        return jsonResult;
    }

    @ApiOperation(value = "设备模糊查询")
    @PostMapping("/getEquipmentLike")
    public JSONResult<List<EquipmentDetailDTO>> getEquipmentLike(@RequestBody EquipmentAddParams equipmentAddParams) {

        List<EquipmentDetailDTO> equipmentDetailDTOS = projectInfoService.selectEquipmentLike(equipmentAddParams);
        JSONResult<List<EquipmentDetailDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(equipmentDetailDTOS);
        return jsonResult;
    }

    @ApiOperation(value = "设备相信信息")
    @GetMapping("/getEquipmentDetailInfo")
    public JSONResult<List<EquipmentDetailInfoDTO>> getEquipmentDetailInfo(Integer id) {

        List<EquipmentDetailInfoDTO> equipment = projectInfoService.getEquipmentDetailInfo(id);
        JSONResult<List<EquipmentDetailInfoDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(equipment);
        return jsonResult;
    }
}
