package com.unais.lms.dto;

public record EnrollmentRequest(
        Long courseId,
        Long studentId
) {
}
