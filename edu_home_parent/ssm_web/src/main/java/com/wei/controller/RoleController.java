package com.wei.controller;

import com.wei.domain.*;
import com.wei.service.MenuService;
import com.wei.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色（条件）
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有角色成功", allRole);

        return responseResult;
    }

    /**
     * 添加&修改角色
     */
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        if (role.getId() == null){
            // 添加
            roleService.saveRole(role);
            return new ResponseResult(true,200,"添加角色成功","");
        }else {
            // 修改
            roleService.updateRole(role);
            return new ResponseResult(true,200,"修改角色成功","");
        }
    }

    /**
     * 查询所有的父子菜单信息(分配菜单的第一个接口)
     */
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        // -1表示查询所有父子级菜单
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        // 响应数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);

        return new ResponseResult(true,200,"查询所有父子菜单信息成功",map);
    }

    /**
     * 根据角色ID查询该角色关联的菜单信息ID
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);

        return new ResponseResult(true,200,"查询角色关联的菜单信息成功",menuByRoleId);
    }

    /**
     * 为角色分配菜单
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);

        return new ResponseResult(true,200,"为角色分配菜单成功",null);
    }

    /**
     * 删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);

        return new ResponseResult(true,200,"删除角色成功","");
    }

    /**
     * 获取当前角色拥有的 资源信息(包括资源分类以及资源信息)
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId){
        List<ResourceCategory> resourceList = roleService.findResourceListByRoleId(roleId);

        return new ResponseResult(true,200,"查询角色拥有的资源信息成功",resourceList);
    }

    /**
     * 为角色分配资源
     */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo){
        roleService.roleContextResource(roleResourceVo);

        return new ResponseResult(true,200,"为角色分配资源成功",null);
    }
}
