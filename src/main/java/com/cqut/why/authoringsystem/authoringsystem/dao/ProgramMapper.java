package com.cqut.why.authoringsystem.authoringsystem.dao;

import com.cqut.why.authoringsystem.authoringsystem.entity.Program;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramVersion;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProgramParams;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProgramMapper {
    Integer selectProgramCount(ProgramParams programParams);

    List<Program> selectProgramPage(ProgramParams programParams);

    Integer selectNum(Integer id);

    List<ProgramVersion> selectProgramVersion(Integer id);

    ProgramVersion loadProgramVersion(String version, String programName);

    int insert(ProgramVersion programVersion);

    ProgramVersion find(Integer id);

    boolean deleteProgramVersion(Integer id);

    Program loadByProgram(String name);

    int insertProgram(Program program);

    Integer selectProgramCount1(ProgramParams programParams);

    List<Program> selectProgramPage1(ProgramParams programParams);

    int insertProgram1(Program program);

    int insert1(ProgramVersion programVersion);
}
