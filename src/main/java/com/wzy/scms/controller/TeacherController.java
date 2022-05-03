package com.wzy.scms.controller;

import com.wzy.scms.entity.StudentCourse;
import com.wzy.scms.entity.Teacher;
import com.wzy.scms.repository.TeacherRepository;
import com.wzy.scms.vo.MinMaxAvgAchievementVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    final
    TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    /**
     * 教师注册
     */
    @PostMapping("/add")
    public String add(@RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
        return "Add Successfully";
    }

    /**
     * 教师修改自己的信息
     */
    @PostMapping("/update")
    public String update(@RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
        return "update Successfully";
    }

    /**
     * 教师删除
     */
    @GetMapping("/delete")
    public String delete(Integer id) {
        teacherRepository.deleteById(id);
        return "delete Successfully";
    }
    /**
     * 教师查看
     */
    @GetMapping("/list")
    public Page<Teacher> list(Integer page) {
        return teacherRepository.findAll(PageRequest.of(page,6));
    }

    /**
     * 教师能查询自己单门课程的选课信息
     */
    @GetMapping("/select-student-course")
    public List<StudentCourse> selectStudentCourse(Integer id) {
        return teacherRepository.selectStudentCourse(id);
    }

    /**
     * 教师能分析自己的所有课程成绩统计信息（每门课的平均分，最高分，最低分等）
     */
    @GetMapping("/select-min-max-avg")
    public List<MinMaxAvgAchievementVo> selectMinMaxAvg(Integer id) {
        return teacherRepository.selectMinMaxAvg(id);
    }
}
