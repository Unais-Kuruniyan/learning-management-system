package com.unais.lms.mappers;

import com.unais.lms.dto.StudentRequest;
import com.unais.lms.dto.StudentResponse;
import com.unais.lms.dto.StudentSummary;
import com.unais.lms.exception.ResourceNotFoundException;
import com.unais.lms.model.Enrollment;
import com.unais.lms.model.Student;
import com.unais.lms.repo.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EnrollmentMapper enrollmentMapper;

    public StudentMapper(UserRepository userRepository, UserMapper userMapper, EnrollmentMapper enrollmentMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.enrollmentMapper = enrollmentMapper;
    }


    public  StudentResponse mapToStudentResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                userMapper.mapToUserResponse(userRepository.findById(student.getUser().getId()).orElseThrow(()-> new ResourceNotFoundException("User not found"))),
//                student.getEnrollments().stream()
//                        .map(enrollmentMapper::mapToEnrollmentResponse).toList()
                student.getEnrollments().stream()
                        .map(Enrollment::getId)
                        .toList()
        );
    }

    public Student mapToStudent(StudentRequest studentRequest) {
        return Student.builder()
                .name(studentRequest.name())
                .user(userRepository.findById(studentRequest.userId()).orElseThrow(()-> new ResourceNotFoundException("user not found")))
                .build();
    }

    public StudentSummary mapToStudentSummary(Student student) {
        return new StudentSummary(
                student.getId(),
                student.getName(),
                userMapper.mapToUserResponse(student.getUser())
        );
    }
}
