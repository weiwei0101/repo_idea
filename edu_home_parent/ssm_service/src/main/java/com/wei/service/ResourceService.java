package com.wei.service;

import com.github.pagehelper.PageInfo;
import com.wei.domain.Resource;
import com.wei.domain.ResourceVo;

public interface ResourceService {

    /**
     * 资源分页&多条件查询
     */
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);

    /**
     * 添加资源
     */
    public void saveResource(Resource resource);

    /**
     * 修改资源
     */
    public void updateResource(Resource resource);

    /**
     * 删除资源
     */
    public void deleteResource(Integer id);
}
