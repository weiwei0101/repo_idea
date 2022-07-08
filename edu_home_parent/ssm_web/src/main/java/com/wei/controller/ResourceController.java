package com.wei.controller;

import com.github.pagehelper.PageInfo;
import com.wei.domain.Resource;
import com.wei.domain.ResourceVo;
import com.wei.domain.ResponseResult;
import com.wei.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 资源分页&多条件查询
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo){
        PageInfo<Resource> allResourceByPage = resourceService.findAllResourceByPage(resourceVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "资源分页&多条件查询成功", allResourceByPage);

        return responseResult;
    }

    /**
     * 添加&更新资源信息
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        if (resource.getId() == null){
            // 添加
            resourceService.saveResource(resource);
            return new ResponseResult(true,200,"添加资源成功",null);
        }else {
            // 修改
            resourceService.updateResource(resource);
            return new ResponseResult(true,200,"修改资源成功",null);
        }
    }

    /**
     * 删除资源
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){
        resourceService.deleteResource(id);
        return new ResponseResult(true,200,"删除资源成功",null);
    }

}
