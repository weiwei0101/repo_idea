package com.wei.dao;

import com.wei.domain.*;

import java.util.List;

public interface RoleMapper {

    /**
     * 查询所有角色&条件进行查询
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
     * 删除角色
     */
    public void deleteRole(Integer rolrid);

    /**
     * 根据角色ID查询该角色关联的菜单信息ID [1,2,3,5]
     */
    public List<Integer> findMenuByRoleId(Integer roleid);

    /**
     * 根据roleid清空中间表单的关联关系
     */
    public void deleteRoleContextMenu(Integer rid);

    /**
     * 为角色分配菜单信息
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /**
     * 根据resource表中的category_id,查询角色拥有的资源分类
     */
    public List<ResourceCategory> findResourceCategoryById(Integer id);

    /**
     * 根据角色ID 查询当前角色拥有的资源信息
     */
    public List<Resource> findResourceByRoleId(Integer roleId);

    /**
     * 根据角色ID 删除角色与资源的关联关系
     */
    public void deleteRoleContextResource(Integer roleId);

    /**
     * 为角色分配资源
     */
    public void roleContextResource(RoleResourceRelation resourceRelation);
}
