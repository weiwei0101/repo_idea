package com.wei.service;

import com.wei.domain.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 查询所有父子菜单信息
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /**
     * 查询所有菜单列表
     */
    public List<Menu> findAllMenu();

    /**
     * 回显菜单信息
     */
    public Menu findMenuById(Integer id);

    /**
     * 添加菜单
     */
    public void saveMenu(Menu menu);

    /**
     * 修改菜单
     */
    public void updateMenu(Menu menu);

    /**
     * 删除菜单
     */
    public void deleteMenu(Integer id);
}
