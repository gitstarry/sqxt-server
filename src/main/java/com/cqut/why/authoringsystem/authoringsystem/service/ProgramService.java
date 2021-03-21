package com.cqut.why.authoringsystem.authoringsystem.service;

import com.cqut.why.authoringsystem.authoringsystem.entity.Program;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramVersion;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProgramVersionDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProgramsDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProgramParams;

import java.util.List;

public interface ProgramService {
    Integer getProgramCount(ProgramParams programParams);

    List<Program> getProgramLog(ProgramParams programParams);

    Integer selectNum(Integer id);

    List<ProgramVersion> getProgramVersion(Integer id);

    boolean addProgramVersion(ProgramVersionDTO programVersionDTO);

    boolean deleteProgramVersion(Integer id);

    boolean addProgram(ProgramsDTO programsDTO);

    Integer getProgramCount1(ProgramParams programParams);

    List<Program> getProgramLog1(ProgramParams programParams);

    boolean addProgram1(ProgramsDTO programsDTO);

    boolean addProgramVersion1(ProgramVersionDTO programVersionDTO);
}
