package com.cqut.why.authoringsystem.authoringsystem.service.servicelmp;

import com.cqut.why.authoringsystem.authoringsystem.dao.ProjectMapper;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramVersion;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectParams;
import com.cqut.why.authoringsystem.authoringsystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
