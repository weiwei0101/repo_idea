package com.wei.controller;

import com.github.pagehelper.PageInfo;
import com.wei.domain.PromotionAd;
import com.wei.domain.PromotionAdVo;
import com.wei.domain.ResponseResult;
import com.wei.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /**
     * 分页查询所有广告信息
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo promotionAdVo){
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "广告分页查询成功", pageInfo);

        return responseResult;
    }

    /**
     * 图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            // 1.判断接收到的上传文件是否为空
            if (file.isEmpty()){
                throw new RuntimeException();
            }

            // 2.获取项目部署路径
            // D:\apache-tomcat-8.5.56\webapps\ssm_web\
            String realPath = request.getServletContext().getRealPath("/");
            // D:\apache-tomcat-8.5.56\webapps
            String substring = realPath.substring(0, realPath.indexOf("ssm_web"));

            // 3.获取原文件名
            // lagou.jpg
            String originalFilename = file.getOriginalFilename();

            // 4.生成新文件名
            // 20220624.jpg
            String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

            // 5.文件上传
            // D:/apache-tomcat-8.5.56/webapps/upload
            String uploadPath = substring + "upload\\";
            File filePath = new File(uploadPath, newFileName);

            // 如果目录不存在就创建目录
            if(!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录：" + filePath);
            }

            // 图片就进行了真正的上传
            file.transferTo(filePath);

            // 6.将文件名和文件路径返回，进行响应
            HashMap<String, String> map = new HashMap<>();
            map.put("fileName",newFileName);
            map.put("filePath","http://localhost:8080/upload/" + newFileName);

            ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);
            return responseResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 新建&修改广告
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if (promotionAd.getId() == null){
            // 新增
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true,200,"新增广告成功",null);
        }else {
            // 修改
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true,200,"修改广告成功",null);

        }
    }

    /**
     * 根据id查询广告信息（回显）
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "根据id查询广告信息成功", promotionAd);

        return responseResult;
    }

    /**
     * 广告状态上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id, Integer status){
        promotionAdService.updatePromotionAdStatus(id,status);

        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "广告动态上下线成功", map);
        return responseResult;
    }
}
