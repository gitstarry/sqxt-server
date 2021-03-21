package com.cqut.why.authoringsystem.authoringsystem.dao;

import com.cqut.why.authoringsystem.authoringsystem.entity.*;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.EquipmentUpgradeDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProjectInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentUpgradeParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectParams;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {

    Integer selectProjectCount(ProjectParams projectParams);

    List<ProgramVersion> selectProjectPage(ProjectParams projectParams);

    ProgramVersion selectId(Integer id);

    Program selectProgram(Integer programId);

    void updateByProgramVersionKey(ProgramVersion programVersion);


    int updateByProgramKey(String remark, Integer programId,String version);

    Integer selectEquipmentCount(EquipmentUpgradeParams equipmentUpgradeParams);

    List<EquipmentUpgradeDTO> selectEquipmentPage(EquipmentUpgradeParams equipmentUpgradeParams);

    Equipment selectEquipmentId(Integer id);

    void updateEquipment(Equipment equipment);

    Program selectProgramId(String programName);

    int insert(ProgramUpgradeLog programUpgradeLog);

    int updateByProgramKey1(String remark, Integer programId);
}
