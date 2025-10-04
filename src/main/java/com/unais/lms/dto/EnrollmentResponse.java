package com.unais.lms.dto;

import java.time.LocalDateTime;

public record  EnrollmentResponse(
        Long enrollmentId,
        LocalDateTime enrollmentDate,
        CourseResponse courseResponse
) {
}
