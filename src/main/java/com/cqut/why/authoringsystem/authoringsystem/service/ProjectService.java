package com.cqut.why.authoringsystem.authoringsystem.service;

import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramVersion;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProjectParams;

import java.util.List;

public interface ProjectService {
    Integer getProjectCount(ProjectParams projectParams);

    List<ProgramVersion> getProjectLog(ProjectParams projectParams);
}
