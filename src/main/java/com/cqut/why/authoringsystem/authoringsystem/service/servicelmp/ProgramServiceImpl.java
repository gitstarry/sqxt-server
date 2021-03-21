package com.cqut.why.authoringsystem.authoringsystem.service.servicelmp;

import com.cqut.why.authoringsystem.authoringsystem.config.util.BeanMapper.BeanMapper;
import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import com.cqut.why.authoringsystem.authoringsystem.dao.ProgramMapper;
import com.cqut.why.authoringsystem.authoringsystem.entity.Program;
import com.cqut.why.authoringsystem.authoringsystem.entity.ProgramVersion;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProgramVersionDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ProgramsDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.ProgramParams;
import com.cqut.why.authoringsystem.authoringsystem.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProgramServiceImpl implements ProgramService {
    @Autowired
    ProgramMapper programMapper;

    @Override
    public Integer getProgramCount(ProgramParams programParams) {
        return programMapper.selectProgramCount(programParams);
    }

    @Override
    public List<Program> getProgramLog(ProgramParams programParams) {
        return programMapper.selectProgramPage(programParams);
    }

    @Override
    public Integer selectNum(Integer id) {
        return programMapper.selectNum(id);
    }

    @Override
    public List<ProgramVersion> getProgramVersion(Integer id) {
        return programMapper.selectProgramVersion(id);
    }

    @Override
    public boolean addProgramVersion(ProgramVersionDTO programVersionDTO) {
        ProgramVersion record = programMapper.loadProgramVersion(programVersionDTO.getVersion(),programVersionDTO.getProgramName());
        if (record != null) {
            throw new BusinessException("已经有该版本");
        }
        ProgramVersion programVersion = BeanMapper.map(programVersionDTO, ProgramVersion.class);
        return programMapper.insert(programVersion) == 1;
    }

    @Override
    public boolean deleteProgramVersion(Integer id) {
        ProgramVersion programVersion = programMapper.find(id);
        if (programVersion == null) {
            throw new BusinessException("信息不存在");
        }
        return programMapper.deleteProgramVersion(id);
    }

    @Override
    public boolean addProgram(ProgramsDTO programsDTO) {
        Program record = programMapper.loadByProgram(programsDTO.getName());
        if (record != null) {
            throw new BusinessException("算法已注册");
        }
        Program program = BeanMapper.map(programsDTO, Program.class);
        return programMapper.insertProgram(program) == 1;
    }

    @Override
    public Integer getProgramCount1(ProgramParams programParams) {
        return programMapper.selectProgramCount1(programParams);
    }

    @Override
    public List<Program> getProgramLog1(ProgramParams programParams) {
        return programMapper.selectProgramPage1(programParams);
    }

    @Override
    public boolean addProgram1(ProgramsDTO programsDTO) {
        Program record = programMapper.loadByProgram(programsDTO.getName());
        if (record != null) {
            throw new BusinessException("算法已注册");
        }
        Program program = BeanMapper.map(programsDTO, Program.class);
        return programMapper.insertProgram1(program) == 1;
    }

    @Override
    public boolean addProgramVersion1(ProgramVersionDTO programVersionDTO) {
        ProgramVersion record = programMapper.loadProgramVersion(programVersionDTO.getVersion(),programVersionDTO.getProgramName());
        if (record != null) {
            throw new BusinessException("已经有该版本");
        }
        ProgramVersion programVersion = BeanMapper.map(programVersionDTO, ProgramVersion.class);
        return programMapper.insert1(programVersion) == 1;
    }
}
