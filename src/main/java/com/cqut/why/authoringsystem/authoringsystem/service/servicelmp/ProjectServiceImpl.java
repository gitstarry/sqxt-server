package com.cqut.why.authoringsystem.authoringsystem.service.servicelmp;

import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import com.cqut.why.authoringsystem.authoringsystem.dao.ProjectMapper;
import com.cqut.why.authoringsystem.authoringsystem.entity.Equipment;
import com.cqut.why.authoringsystem.authoringsystem.entity.Program;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramUpgradeLog;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramVersion;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.EquipmentUpgradeDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.EquipmentUpgradeStatusDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProjectInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentUpgradeParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectParams;
import com.cqut.why.authoringsystem.authoringsystem.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    @Override
    public Integer getProjectCount(ProjectParams projectParams) {
        return projectMapper.selectProjectCount(projectParams);
    }

    @Override
    public List<ProgramVersion> getProjectLog(ProjectParams projectParams) {
        return projectMapper.selectProjectPage(projectParams);
    }

    @Override
    public boolean modifyUser(ProjectInfoDTO projectInfoDTO) {
        ProgramVersion programVersion = projectMapper.selectId(projectInfoDTO.getId());
        if (programVersion == null) {
            throw new BusinessException("该测试不存在");
        }
        Program program = projectMapper.selectProgram(programVersion.getProgramId());
        if(program == null) {
            throw new BusinessException("该测试不存在");
        }
        BeanUtils.copyProperties(projectInfoDTO, programVersion);
        projectMapper.updateByProgramVersionKey(programVersion);
        if(programVersion.getStatus() != 3) {
            return projectMapper.updateByProgramKey(projectInfoDTO.getRemark(), programVersion.getProgramId(), programVersion.getVersion()) == 1;
        }
        return projectMapper.updateByProgramKey1(projectInfoDTO.getRemark(), programVersion.getProgramId()) == 1;
    }

    @Override
    public Integer getEquipmentCount(EquipmentUpgradeParams equipmentUpgradeParams) {
        return projectMapper.selectEquipmentCount(equipmentUpgradeParams);
    }

    @Override
    public List<EquipmentUpgradeDTO> getEquipmentLog(EquipmentUpgradeParams equipmentUpgradeParams) {
        return projectMapper.selectEquipmentPage(equipmentUpgradeParams);
    }

    @Override
    public boolean modifyEquipment(EquipmentUpgradeStatusDTO equipmentUpgradeStatusDTO) {
        Equipment equipment = projectMapper.selectEquipmentId(equipmentUpgradeStatusDTO.getId());
        if(equipment == null) {
            throw new BusinessException("该设备不存在");
        }

        BeanUtils.copyProperties(equipmentUpgradeStatusDTO, equipment);
        equipment.setUpgradeStatus(0);
        equipment.setUpdatedAt(new Date());
        projectMapper.updateEquipment(equipment);

        Program program = projectMapper.selectProgramId(equipmentUpgradeStatusDTO.getProgramName());

        ProgramUpgradeLog programUpgradeLog = new ProgramUpgradeLog();
        BeanUtils.copyProperties(equipment, programUpgradeLog);
        programUpgradeLog.setEquipmentName(equipment.getName());
        programUpgradeLog.setEquipmentId(equipment.getId());
        programUpgradeLog.setUpgradeAt(equipment.getUpdatedAt());
        programUpgradeLog.setEquipmentSn(equipment.getSn());
        programUpgradeLog.setUpgradeUserId(equipment.getUpdateUserId());
        programUpgradeLog.setProgramCategory(program.getCategory());
        programUpgradeLog.setProgramVersion(program.getLatestVersion());
        programUpgradeLog.setProgramName(program.getName());
        programUpgradeLog.setProjectName(equipment.getProjectName());
        programUpgradeLog.setRemark(program.getDescription());
        programUpgradeLog.setProgramId(program.getId());
        return projectMapper.insert(programUpgradeLog) == 1;
    }
}
