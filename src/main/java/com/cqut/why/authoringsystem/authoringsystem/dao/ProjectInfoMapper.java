package com.cqut.why.authoringsystem.authoringsystem.dao;

import com.cqut.why.authoringsystem.authoringsystem.entity.Equipment;
import com.cqut.why.authoringsystem.authoringsystem.entity.LicenseMigrationDetail;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.*;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentAddParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectAllParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectInfoParams;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectInfoMapper {


    Boolean addProjectInfo(ProjectInfoParams projectInfoDTO); // 添加客户信息
    Boolean addProject(ProjectInfoParams projectInfoParams); // 添加项目信息

    Boolean deleteProject(Integer id);

    int updateProjectInfo(ProjectInfoParams projectInfoParams);

    int updateCustomer(ProjectInfoParams projectInfoParams);


    // 新增设备
    Boolean addEquipment(EquipmentAddParams equipmentAddParams);

    // 设备详情
    EquipmentDetailInfoDTO getEquipmentDetailInfo(Integer id);

    Integer selectProjectCount(ProjectAllParams projectAllParams);

    List<ProjectInfoDTO> selectProjectPage(ProjectAllParams projectAllParams);

    List<LicenseMigrationDetail> selectEquipment(Integer id);

    List<LicenseMigrationDetail> selectAllEquipmentInfo(Integer id);

    ProjectInfoParams selectId(Integer id);

    int selectProjectId(String name);

    Boolean checkProject(int id);

    Integer selectEquipmentCount(EquipmentAddParams equipmentAddParams);

    List<Equipment> selectEquipmentPage(EquipmentAddParams equipmentAddParams);

    int selectEquipmentId(String name);

    Boolean checkEquipment(int id);

    boolean deleteEquipment(Integer id);
}

