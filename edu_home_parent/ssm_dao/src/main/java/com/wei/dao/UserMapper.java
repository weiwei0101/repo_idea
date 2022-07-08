package com.wei.dao;

import com.wei.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserMapper {

    /**
     * 用户分页&多条件组合查询
     */
    public List<User> findAllUserByPage(UserVo userVo);

    /**
     * 用户状态设置
     */
    public void updateUserStatus(@Param("id") int id, @Param("status") String status);

    /**
     * 用户登录(根据用户名查询具体用户信息)
     */
    public User login(User user);

    /**
     * 根据用户ID清空中间表
     */
    public void deleteUserContextRole(Integer userId);

    /**
     * 分配角色：向中间表添加记录
     */
    public void userContextRole(User_Role_relation user_role_relation);

    /**
     * 1.分配角色（回显）：根据用户ID查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /**
     * 2.根据角色ID，查询角色所拥有的顶级菜单（-1）
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /**
     * 3.根据PID，查询子菜单信息
     */
    public List<Menu> findSubMenuByPid(Integer pid);

    /**
     * 4.获取用户拥有的资源权限信息
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);
    
    public List<Resource> findResourceByRoleId2(List<Integer> ids);
    
    public void test11();
    public void test21();
    public void test31();
    public void test41();
    public void test51();
    public void test61();
    public void test71();
}
