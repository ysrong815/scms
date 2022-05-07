package com.wzy.scms.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherCourseVo {
    String studentCode;
    String studentName;
    String courseName;
    Float achievement;
}
