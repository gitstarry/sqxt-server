package com.cqut.why.authoringsystem.authoringsystem.service.servicelmp;


import com.cqut.why.authoringsystem.authoringsystem.dao.ProjectInfoMapper;
import com.cqut.why.authoringsystem.authoringsystem.entity.Equipment;
import com.cqut.why.authoringsystem.authoringsystem.entity.LicenseMigrationDetail;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.*;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentAddParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectAllParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectInfoParams;
import com.cqut.why.authoringsystem.authoringsystem.service.ProjectInfoService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectInfoImpl implements ProjectInfoService {

    @Autowired
    ProjectInfoMapper projectInfoMapper;

    @Override
    public Boolean addProjectInfo(ProjectInfoParams projectInfoParams) {
        if (projectInfoMapper.addProjectInfo(projectInfoParams))
            return projectInfoMapper.addProject(projectInfoParams);
        else return false;
    }

    @Override
    public Boolean updateProjectInfo(ProjectInfoParams projectInfoParams) {
        if(projectInfoMapper.updateProjectInfo(projectInfoParams) == 1) {
            return projectInfoMapper.updateCustomer(projectInfoParams) == 1;
        }
        else return false;
    }

    @Override
    public List<LicenseMigrationDetail> selectAllEquipmentInfo(Integer id) {
        return projectInfoMapper.selectAllEquipmentInfo(id);
    }


    @Override
    public Boolean deleteProject(Integer id) {
        return projectInfoMapper.deleteProject(id);
    }

    @Override
    public Boolean addEquipment(EquipmentAddParams equipmentAddParams) {
        return projectInfoMapper.addEquipment(equipmentAddParams);
    }

    @Override
    public EquipmentDetailInfoDTO getEquipmentDetailInfo(Integer id) {
        return projectInfoMapper.getEquipmentDetailInfo(id);
    }

    @Override
    public Integer getCount(ProjectAllParams projectAllParams) {
        return projectInfoMapper.selectProjectCount(projectAllParams);
    }

    @Override
    public List<ProjectInfoDTO> getProjectLog(ProjectAllParams projectAllParams) {
        return projectInfoMapper.selectProjectPage(projectAllParams);
    }

    @Override
    public ProjectInfoParams getById(Integer id) {
        return projectInfoMapper.selectId(id);
    }

    @Override
    public int selectProjectId(String name) {
        return projectInfoMapper.selectProjectId(name);
    }

    @Override
    public Boolean checkProject(int id) {
        return projectInfoMapper.checkProject(id);
    }

    @Override
    public Integer getEquipmentCount(EquipmentAddParams equipmentAddParams) {
        return projectInfoMapper.selectEquipmentCount(equipmentAddParams);
    }

    @Override
    public List<Equipment> getEquipmentLog(EquipmentAddParams equipmentAddParams) {
        return projectInfoMapper.selectEquipmentPage(equipmentAddParams);
    }

    @Override
    public int selectEquipmentId(String name) {
        return projectInfoMapper.selectEquipmentId(name);
    }

    @Override
    public Boolean checkEquipment(int id) {
        return projectInfoMapper.checkEquipment(id);
    }

    @Override
    public boolean deleteEquipment(Integer id) {
        return projectInfoMapper.deleteEquipment(id);
    }
}
