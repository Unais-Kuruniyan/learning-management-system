package com.unais.lms.dto;

import com.unais.lms.model.Course;
import com.unais.lms.model.Student;

public record EnrollmentRequest(
        Course course,
        Student student
) {
}
