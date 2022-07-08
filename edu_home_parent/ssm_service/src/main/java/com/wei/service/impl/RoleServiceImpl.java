package com.wei.service.impl;

import com.wei.dao.RoleMapper;
import com.wei.domain.*;
import com.wei.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public void saveRole(Role role) {
        // 补全数据
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");

        // 调用mapper
        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {
        // 补全数据
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);

        // 调用mapper
        roleMapper.updateRole(role);
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        // 1.清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        // 2.为角色分配菜单
        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            // 封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer roleid) {
        // 调用根据roleid清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleid);

        roleMapper.deleteRole(roleid);
    }

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId) {
        // 1.获取角色拥有的资源分类数据
        List<ResourceCategory> catrgoryList = roleMapper.findResourceCategoryById(roleId);

        // 2.获取角色拥有的资源数据
        List<Resource> resourceList = roleMapper.findResourceByRoleId(roleId);

        // 3.将资源数据封装到对应分类下
        for (ResourceCategory category : catrgoryList) {
            for (Resource resource : resourceList) {
                // 判断
                if (category.getId() == resource.getCategoryId()){
                    // 将资源保存到集合中
                    category.getResourceList().add(resource);
                }
            }
        }

        // 4.返回资源分类集合
        return catrgoryList;
    }

    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {
        // 根据角色id 清空中间表
        roleMapper.deleteRoleContextResource(roleResourceVo.getRoleId());

        // 获取分配资源的id集合
        List<Integer> resourceIdList = roleResourceVo.getResourceIdList();

        // 向中间表插入最新的关联信息
        for (Integer resourceId : resourceIdList) {
            // 封装数据
            RoleResourceRelation relation = new RoleResourceRelation();
            relation.setRoleId(roleResourceVo.getRoleId());
            relation.setResourceId(resourceId);

            Date date = new Date();
            relation.setCreatedTime(date);
            relation.setUpdatedTime(date);

            relation.setCreatedBy("system");
            relation.setUpdatedBy("system");

            roleMapper.roleContextResource(relation);
        }
    }
}
