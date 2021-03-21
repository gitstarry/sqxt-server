package com.cqut.why.authoringsystem.authoringsystem.service;


import com.cqut.why.authoringsystem.authoringsystem.entity.dto.EquipmentDetailDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.EquipmentDetailInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.EquipmentInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProjectInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentAddParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectInfoParams;

import java.util.List;

public interface ProjectInfoService {
    List<ProjectInfoDTO> selectAllProjectInfo();

    List<ProjectInfoDTO> selectAllLike(ProjectInfoParams projectInfoParams);

    Boolean addProjectInfo(ProjectInfoParams projectInfoParams);

    int updateProjectInfo(ProjectInfoParams projectInfoParams);

    List<EquipmentInfoDTO> selectAllEquipmentInfo(String prjName);

    List<EquipmentDetailDTO> selectAllEquipmentDetail();
    Boolean deleteProject(Integer id);
    Boolean addEquipment(EquipmentAddParams equipmentAddParams);
    List<EquipmentDetailDTO> selectEquipmentLike(EquipmentAddParams equipmentAddParams);

    List<EquipmentDetailInfoDTO> getEquipmentDetailInfo(Integer id);
}
