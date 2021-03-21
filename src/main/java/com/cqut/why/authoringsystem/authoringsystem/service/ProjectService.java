package com.cqut.why.authoringsystem.authoringsystem.service;

import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramVersion;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.EquipmentUpgradeDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.EquipmentUpgradeStatusDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProjectInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentUpgradeParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectParams;

import java.util.List;

public interface ProjectService {
    Integer getProjectCount(ProjectParams projectParams);

    List<ProgramVersion> getProjectLog(ProjectParams projectParams);

    boolean modifyUser(ProjectInfoDTO projectInfoDTO);

    Integer getEquipmentCount(EquipmentUpgradeParams equipmentUpgradeParams);

    List<EquipmentUpgradeDTO> getEquipmentLog(EquipmentUpgradeParams equipmentUpgradeParams);

    boolean modifyEquipment(EquipmentUpgradeStatusDTO equipmentUpgradeStatusDTO);
}
