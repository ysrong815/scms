package com.wzy.scms.controller;

import com.wzy.scms.entity.Student;
import com.wzy.scms.repository.StudentCourseRepository;
import com.wzy.scms.repository.StudentRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    final
    StudentRepository studentRepository;
    final
    StudentCourseRepository studentCourseRepository;

    public StudentController(StudentRepository studentRepository, StudentCourseRepository studentCourseRepository) {
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    /**
     * 学生的增加。code不能重复, 用户名不能重复。
     */
    @PostMapping("/add")
    public String add(@RequestBody Student student) {
        if (studentRepository.getCode(student.getCode()) != null || studentRepository.getName(student.getName()) != null) {
            return "Add Failed";
        }
        studentRepository.save(student);
        return "Add Successfully";
    }

    /**
     * 删除学生，实现根据id删除学生，有选课的学生，必须把选课记录删除。
     */
    @PostMapping("/delete")
    public String delete(Integer id) {
        if (studentRepository.findById(id).isPresent()) {
            if (studentCourseRepository.findById(id).isPresent()) {
                studentCourseRepository.deleteById(id);
            }
            studentRepository.deleteById(id);
            return "Delete Successfully";
        } else {
            return "Delete Failed";
        }
    }

    /**
     * 修改学生，根据id，修改学生的数据。
     */
    @PostMapping("/update")
    public String update(Integer id, @RequestBody Student student) {
        student.setId(id);
        studentRepository.save(student);
        return "Update Successfully";
    }

    /**
     * 实现根据姓名和密码的登录接口
     */
    @PostMapping("/login-name-password")
    public String loginNamePassword(String name, String password) {
        System.out.println("@@@@@@@" + studentRepository.getNamePassword(name, password));
        if (studentRepository.getNamePassword(name, password) == null) {
            return "Login Failed";
        }
        return "Login successfully";
    }
}
