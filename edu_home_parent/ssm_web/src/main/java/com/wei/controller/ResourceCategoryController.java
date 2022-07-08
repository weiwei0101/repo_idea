package com.wei.controller;

import com.wei.domain.ResourceCategory;
import com.wei.domain.ResponseResult;
import com.wei.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /**
     * 查询所有资源分类
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有资源分类成功", allResourceCategory);

        return responseResult;
    }

    /**
     * 添加&修改资源分类
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){
        // 判断是否携带资源分类的ID
        if (resourceCategory.getId() == null){
            // 添加
            resourceCategoryService.saveResourceCategory(resourceCategory);
            return new ResponseResult(true,200,"添加资源分类成功",null);
        }else {
            // 修改
            resourceCategoryService.updateResourceCategory(resourceCategory);
            return new ResponseResult(true,200,"修改资源分类成功",null);
        }
    }

    /**
     * 删除资源分类
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        resourceCategoryService.deleteResourceCategory(id);

        return new ResponseResult(true,200,"删除资源分类成功",null);
    }
}
