package com.unais.lms.mappers;

import com.unais.lms.dto.EnrollmentRequest;
import com.unais.lms.dto.EnrollmentResponse;
import com.unais.lms.model.Enrollment;
import com.unais.lms.service.CourseService;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class EnrollmentMapper {
    private final CourseService courseService;

    public EnrollmentMapper(CourseService courseService) {
        this.courseService = courseService;
    }


    public EnrollmentResponse mapToEnrollmentResponse(Enrollment enrollment) {
        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getEnrollmentDate(),
                courseService.getCourseById(enrollment.getCourse().getId())

        );
    }

    public Enrollment mapToEnrollment(EnrollmentRequest enrollmentRequest) {
         new Enrollment();
         return Enrollment.builder()
                 .enrollmentDate(LocalDateTime.now())
                 .course(enrollmentRequest.course())
                 .student(enrollmentRequest.student())
                 .build();
    }
}
