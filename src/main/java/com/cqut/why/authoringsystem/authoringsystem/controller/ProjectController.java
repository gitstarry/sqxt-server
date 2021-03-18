package com.cqut.why.authoringsystem.authoringsystem.controller;

import com.cqut.why.authoringsystem.authoringsystem.config.util.BeanMapper.BeanMapper;
import com.cqut.why.authoringsystem.authoringsystem.config.util.jsonResult.JSONResult;
import com.cqut.why.authoringsystem.authoringsystem.entity.License;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramVersion;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.LicenseDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProjectDTO;
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
}
