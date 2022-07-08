package com.wei.controller;

import com.wei.domain.Course;
import com.wei.domain.CourseVO;
import com.wei.domain.ResponseResult;
import com.wei.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 查询课程信息&条件查询 接口
     * @param courseVO
     * @return
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        // 调用service
        List<Course> list = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",list);
        return responseResult;
    }

    /**
     * 图片上传接口
     */
    @RequestMapping("/courseUpload")
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
     * 新增课程信息及讲师信息
     * 新增课程信息和修改课程信息要写在同一个方法中
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        if (courseVO.getId() == null) {
            // 新增 调用service方法
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }
    }

    /**
     * 根据课程id查询具体的课程信息及关联的讲师信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseVO = courseService.findCourseById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "根据ID查询课程信息成功", courseVO);
        return responseResult;
    }

    /**
     * 修改课程状态
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id, Integer status){
        // 调用service，传递参数，完成课程状态的变更
        courseService.updateCourseStatus(id,status);

        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改课程状态成功", map);
        return responseResult;
    }
}
