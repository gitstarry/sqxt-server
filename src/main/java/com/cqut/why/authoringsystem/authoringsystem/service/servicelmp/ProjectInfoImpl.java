package com.cqut.why.authoringsystem.authoringsystem.service.servicelmp;



import com.cqut.why.authoringsystem.authoringsystem.dao.ProjectInfoMapper;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProjectInfo;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.EquipmentDetailDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.EquipmentDetailInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.EquipmentInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProjectInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.EquipmentAddParams;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectInfoParams;
import com.cqut.why.authoringsystem.authoringsystem.service.ProjectInfoService;
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
    public List<ProjectInfoDTO> selectAllProjectInfo() {
        return projectInfoMapper.selectAllProjectInfo();
    }

    @Override
    public List<ProjectInfoDTO> selectAllLike(ProjectInfoParams projectInfoParams) {
        return projectInfoMapper.selectAllLike(projectInfoParams);
    }

    @Override
    public Boolean addProjectInfo(ProjectInfoParams projectInfoParams) {

        if (projectInfoMapper.addProjectInfo(projectInfoParams))
            return projectInfoMapper.addProject(projectInfoParams);
        else return false;
    }

    @Override
    public int updateProjectInfo(ProjectInfoParams projectInfoParams) {
        if(projectInfoMapper.updateProjectInfo(projectInfoParams) == 1)
            return projectInfoMapper.updateCustomer(projectInfoParams);
        else return -1;
    }

    @Override
    public List<EquipmentInfoDTO> selectAllEquipmentInfo(String prjName) {
        return projectInfoMapper.selectAllEquipmentInfo(prjName);
    }

    @Override
    public List<EquipmentDetailDTO> selectAllEquipmentDetail() {
        return projectInfoMapper.selectAllEquipmentDetail();
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
    public List<EquipmentDetailDTO> selectEquipmentLike(EquipmentAddParams equipmentAddParams) {
        return projectInfoMapper.selectEquipmentLike(equipmentAddParams);
    }

    @Override
    public List<EquipmentDetailInfoDTO> getEquipmentDetailInfo(Integer id) {
        return projectInfoMapper.getEquipmentDetailInfo(id);
    }
}
