package com.unais.lms.dto;

public record CourseResponse(
        Long courseId,
        String courseName,
        String courseCode,
        String courseDescription

) {
}
