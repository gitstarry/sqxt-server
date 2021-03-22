package com.cqut.why.authoringsystem.authoringsystem.service;


import com.cqut.why.authoringsystem.authoringsystem.entity.Equipment;
import com.cqut.why.authoringsystem.authoringsystem.entity.LicenseMigrationDetail;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.*;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentAddParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectAllParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectInfoParams;

import java.util.List;

public interface ProjectInfoService {

    Boolean addProjectInfo(ProjectInfoParams projectInfoParams);

    Boolean updateProjectInfo(ProjectInfoParams projectInfoParams);

    List<LicenseMigrationDetail> selectAllEquipmentInfo(Integer id);

    Boolean deleteProject(Integer id);
    Boolean addEquipment(EquipmentAddParams equipmentAddParams);

    EquipmentDetailInfoDTO getEquipmentDetailInfo(Integer id);

    Integer getCount(ProjectAllParams projectAllParams);

    List<ProjectInfoDTO> getProjectLog(ProjectAllParams projectAllParams);

    ProjectInfoParams getById(Integer id);

    int selectProjectId(String name);

    Boolean checkProject(int id);

    Integer getEquipmentCount(EquipmentAddParams equipmentAddParams);

    List<Equipment> getEquipmentLog(EquipmentAddParams equipmentAddParams);

    int selectEquipmentId(String name);

    Boolean checkEquipment(int id);

    boolean deleteEquipment(Integer id);
}
