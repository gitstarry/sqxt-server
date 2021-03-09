package com.cqut.why.authoringsystem.authoringsystem.dao;

import com.cqut.why.authoringsystem.authoringsystem.entity.Permission;
import com.cqut.why.authoringsystem.authoringsystem.entity.Role;
import com.cqut.why.authoringsystem.authoringsystem.entity.User;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ChangePasswordDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.PermissionEntity;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.SysAddUserInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.SysUserQueryParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {


    User findByUsername(String userName);

    /**
     * 根据用户查询权限
     *
     * @param userName
     * @return
     */
    List<PermissionEntity> findPermissionByUsername(String userName);

    User getUserByUsername(String username);

    void changePassword(ChangePasswordDTO changePasswordDTO);

    User loadByUserName(String username);

    Role getRole(Integer id);

    Integer selectCount(SysUserQueryParams sysUserQueryParams);

    List<User> selectPage(SysUserQueryParams sysUserQueryParams);

    User getUserById(Integer id);

    int insert(User user);

    int updateByPrimaryKey(User user);

    int changeStatus(@Param("id") Integer id ,@Param("status") Integer status);

    boolean deleteUser(Integer id);
}
