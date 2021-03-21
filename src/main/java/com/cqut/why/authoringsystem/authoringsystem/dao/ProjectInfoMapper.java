package com.cqut.why.authoringsystem.authoringsystem.dao;

import com.cqut.why.authoringsystem.authoringsystem.entity.dto.*;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentAddParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectInfoParams;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectInfoMapper {

    List<ProjectInfoDTO> selectAllProjectInfo();

    List<ProjectInfoDTO> selectAllLike(ProjectInfoParams projectInfoParams);

    Boolean addProjectInfo(ProjectInfoParams projectInfoParams); // 添加客户信息
    Boolean addProject(ProjectInfoParams projectInfoParams); // 添加项目信息

    Boolean deleteProject(Integer id);

    int updateProjectInfo(ProjectInfoParams projectInfoParams);

    int updateCustomer(ProjectInfoParams projectInfoParams);

    // 查询所有设备（项目管理展开）
    List<EquipmentInfoDTO> selectAllEquipmentInfo(String prjName);
    // 查询设备信息（设备管理）
    List<EquipmentDetailDTO> selectAllEquipmentDetail();
    // 新增设备
    Boolean addEquipment(EquipmentAddParams equipmentAddParams);
    // 设备模糊查询
    List<EquipmentDetailDTO> selectEquipmentLike(EquipmentAddParams equipmentAddParams);
    // 设备详情
    List<EquipmentDetailInfoDTO> getEquipmentDetailInfo(Integer id);

}

