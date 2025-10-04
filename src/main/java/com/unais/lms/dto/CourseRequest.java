package com.unais.lms.dto;

public record CourseRequest(
        String courseName,
        String courseCode,
        String courseDescription
) {
}
