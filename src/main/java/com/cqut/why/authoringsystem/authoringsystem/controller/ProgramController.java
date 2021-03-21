package com.cqut.why.authoringsystem.authoringsystem.controller;

import com.cqut.why.authoringsystem.authoringsystem.config.util.BeanMapper.BeanMapper;
import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import com.cqut.why.authoringsystem.authoringsystem.config.util.jsonResult.JSONResult;
import com.cqut.why.authoringsystem.authoringsystem.config.util.message.Message;
import com.cqut.why.authoringsystem.authoringsystem.entity.Program;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramVersion;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProgramDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProgramVersionDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProgramsDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProgramParams;
import com.cqut.why.authoringsystem.authoringsystem.service.ProgramService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Api(tags = "工厂")
@RequestMapping("/program")
public class ProgramController {
    @Autowired
    ProgramService programService;

    @ApiOperation(value = "分页模糊查询工厂信息")
    @PostMapping("/getProgram")
    public JSONResult<List<ProgramDTO>> getProgram(@RequestBody ProgramParams programParams) {
        // 用户总数
        Integer connectCount = programService.getProgramCount(programParams);
        List<Program> sysLog = programService.getProgramLog(programParams);
        for(int i = 0; i < sysLog.size(); i++) {
            Program program = sysLog.get(i);
            Integer num = programService.selectNum(sysLog.get(i).getId());
            program.setNum(num);
            sysLog.set(i,program);
        }
        List<ProgramDTO> logListDTOS = BeanMapper.mapList(sysLog, ProgramDTO.class);

        JSONResult<List<ProgramDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(logListDTOS);
        jsonResult.setTotalCount(connectCount);
        return jsonResult;
    }

    @ApiOperation(value = "分页模糊查询工厂信息")
    @PostMapping("/getProgramVersion")
    public JSONResult<List<ProgramVersionDTO>> getProgramVersion(Integer id) {
        List<ProgramVersion> sysLog = programService.getProgramVersion(id);
        List<ProgramVersionDTO> logListDTOS = BeanMapper.mapList(sysLog, ProgramVersionDTO.class);
        JSONResult<List<ProgramVersionDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(logListDTOS);
        return jsonResult;
    }

    @ApiOperation(value = "升级信息")
    @PostMapping("/addProgramVersion")
    public JSONResult addProgramVersion(@RequestBody ProgramVersionDTO programVersionDTO) {
        JSONResult jsonResult = new JSONResult();
        boolean flag = programService.addProgramVersion(programVersionDTO);
        if (flag) {
            jsonResult.setMessage(new Message("DB.UPDATE_SUCCESS", "升级"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.UPDATE_FIELD", "升级"));
        }
    }

    @ApiOperation("删除算法信息")
    @GetMapping("/deleteProgramVersion")
    public JSONResult deleteProgramVersion(@RequestParam("id") Integer id) {
        JSONResult jsonResult = new JSONResult();
        boolean delete = programService.deleteProgramVersion(id);
        if (delete) {
            jsonResult.setMessage(new Message("DB.DELETE_SUCCESS", "算法版本"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.DELETE_FAILED", "算法版本"));
        }
    }

    @ApiOperation(value = "新增算法信息")
    @PostMapping("/addProgram")
    public JSONResult addProgram(@RequestBody ProgramsDTO programsDTO) {
        JSONResult jsonResult = new JSONResult();
        boolean flag = programService.addProgram(programsDTO);
        if (flag) {
            jsonResult.setMessage(new Message("DB.ADD_SUCCESS", "算法"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.ADD_FAILED", "算法"));
        }
    }

    @ApiOperation(value = "分页模糊查询工厂信息")
    @PostMapping("/getProgram1")
    public JSONResult<List<ProgramDTO>> getProgram1(@RequestBody ProgramParams programParams) {
        // 用户总数
        Integer connectCount = programService.getProgramCount1(programParams);
        List<Program> sysLog = programService.getProgramLog1(programParams);
        for(int i = 0; i < sysLog.size(); i++) {
            Program program = sysLog.get(i);
            Integer num = programService.selectNum(sysLog.get(i).getId());
            program.setNum(num);
            sysLog.set(i,program);
        }
        List<ProgramDTO> logListDTOS = BeanMapper.mapList(sysLog, ProgramDTO.class);

        JSONResult<List<ProgramDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(logListDTOS);
        jsonResult.setTotalCount(connectCount);
        return jsonResult;
    }

    @ApiOperation(value = "新增程序信息")
    @PostMapping("/addProgram1")
    public JSONResult addProgram1(@RequestBody ProgramsDTO programsDTO) {
        JSONResult jsonResult = new JSONResult();
        boolean flag = programService.addProgram1(programsDTO);
        if (flag) {
            jsonResult.setMessage(new Message("DB.ADD_SUCCESS", "程序"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.ADD_FAILED", "程序"));
        }
    }

    @ApiOperation(value = "升级信息")
    @PostMapping("/addProgramVersion1")
    public JSONResult addProgramVersion1(@RequestBody ProgramVersionDTO programVersionDTO) {
        JSONResult jsonResult = new JSONResult();
        boolean flag = programService.addProgramVersion1(programVersionDTO);
        if (flag) {
            jsonResult.setMessage(new Message("DB.UPDATE_SUCCESS", "升级"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.UPDATE_FIELD", "升级"));
        }
    }
}
