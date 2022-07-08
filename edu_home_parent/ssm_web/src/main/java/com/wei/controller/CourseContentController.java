package com.wei.controller;

import com.wei.domain.Course;
import com.wei.domain.CourseLesson;
import com.wei.domain.CourseSection;
import com.wei.domain.ResponseResult;
import com.wei.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;


    /**
     * 根据课程ID查询课程内容（章节+课时）
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){
        // 调用service
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "章节及课时内容查询成功", list);

        return responseResult;
    }

    /**
     * 回显章节对课时信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询课时信息成功", course);
        return responseResult;
    }

    /**
     * 新增章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        // 判断是否携带了章节ID
        if (courseSection.getId() == null) {
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true, 200, "新增章节成功", null);
        }else {
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true, 200, "更新章节成功", null);
        }
    }

    /**
     * 修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id, int status){
        courseContentService.updateSectionStatus(id, status);

        // 数据响应
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节状态成功", map);
        return responseResult;
    }

    /**
     * 新增课时信息
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson){
        // 判断是否携带了课时ID
        if(courseLesson.getId() == null) {
            courseContentService.saveLesson(courseLesson);
            return new ResponseResult(true, 200, "新增课时信息成功", null);
        }else {
            courseContentService.updateLesson(courseLesson);
            return new ResponseResult(true, 200, "修改课时信息成功", null);
        }
    }
}
