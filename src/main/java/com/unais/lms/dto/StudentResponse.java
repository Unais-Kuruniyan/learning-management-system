package com.unais.lms.dto;

import java.util.List;

public record StudentResponse(
        Long studentId,
        String name,
        UserResponse userResponse,
//        List<EnrollmentResponse> enrollmentResponses
        List<Long> enrollmentIds
) {
}
