package com.wei.service;

import com.github.pagehelper.PageInfo;
import com.wei.domain.ResponseResult;
import com.wei.domain.Role;
import com.wei.domain.User;
import com.wei.domain.UserVo;

import java.util.List;

public interface UserService {

    /**
     * 用户分页&多条件查询
     */
    public PageInfo findAllUserByPage(UserVo userVo);

    /**
     * 用户状态设置
     */
    public void updateUserStatus(int id,String status);

    /**
     * 用户登录（根据用户名查询具体的用户信息）
     */
    public User login(User user) throws Exception;

    /**
     * 分配角色（回显）：根据用户ID查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /**
     * 用户关联角色
     */
    public void userContextRole(UserVo userVo);

    /**
     * 获取用户权限，进行菜单动态展示
     */
    public ResponseResult getUserPermissions(Integer userid);
}
