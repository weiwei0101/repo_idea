package com.wei.service;

import com.wei.domain.*;

import java.util.List;

public interface RoleService {

    /**
     * 查询所有角色（条件）
     */
    public List<Role> findAllRole(Role role);

    /*
     * 添加角色
     */
    public void saveRole(Role role);

    /*
     * 更新角色
     */
    public void updateRole(Role role);

    /**
     * 根据角色ID查询该角色关联的菜单信息ID [1,2,3,5]
     */
    public List<Integer> findMenuByRoleId(Integer roleid);

    /**
     * 为角色分配菜单信息
     */
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    /**
     * 删除角色
     */
    public void deleteRole(Integer roleid);

    /**
     * 获取当前角色拥有的资源信息
     */
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId);

    /**
     * 为角色分配资源
     */
    public void roleContextResource(RoleResourceVo roleResourceVo);
}
