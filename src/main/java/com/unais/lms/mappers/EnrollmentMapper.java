package com.unais.lms.mappers;

import com.unais.lms.dto.EnrollmentRequest;
import com.unais.lms.dto.EnrollmentResponse;
import com.unais.lms.dto.StudentSummary;
import com.unais.lms.model.Enrollment;
import com.unais.lms.repo.CourseRepository;
import com.unais.lms.repo.StudentRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class EnrollmentMapper {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final StudentRepository studentRepository;
    private final UserMapper userMapper;

    public EnrollmentMapper(CourseRepository courseRepository, CourseMapper courseMapper, StudentRepository studentRepository, UserMapper userMapper) {

        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.studentRepository = studentRepository;
        this.userMapper = userMapper;
    }


    public EnrollmentResponse mapToEnrollmentResponse(Enrollment enrollment) {
        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getEnrollmentDate(),
                courseMapper.mapToCourseResponse(courseRepository.findById(enrollment.getCourse().getId()).orElseThrow()),
                new StudentSummary(
                        enrollment.getStudent().getId(),
                        enrollment.getStudent().getName(),
                        userMapper.mapToUserResponse(enrollment.getStudent().getUser())
                )
        );
    }

    public Enrollment mapToEnrollment(EnrollmentRequest enrollmentRequest) {
         new Enrollment();
         return Enrollment.builder()
                 .enrollmentDate(LocalDateTime.now())
                 .course(courseRepository.findById(enrollmentRequest.courseId()).orElseThrow())
                 .student(studentRepository.findById(enrollmentRequest.studentId()).orElseThrow())
                 .build();
    }
}
