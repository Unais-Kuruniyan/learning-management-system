package com.unais.lms.mappers;

import com.unais.lms.dto.StudentRequest;
import com.unais.lms.dto.StudentResponse;
import com.unais.lms.exception.ResourceNotFoundException;
import com.unais.lms.model.Student;
import com.unais.lms.repo.UserRepository;
import com.unais.lms.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    private final UserService userService;
    private final UserRepository userRepository;
    private final EnrollmentMapper enrollmentMapper;

    public StudentMapper(UserService userService, UserRepository userRepository, EnrollmentMapper enrollmentMapper) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.enrollmentMapper = enrollmentMapper;
    }


    public  StudentResponse mapToStudentResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                userService.getUserById(student.getUser().getId()),
                student.getEnrollments().stream()
                        .map(enrollmentMapper::mapToEnrollmentResponse).toList()
        );
    }

    public Student mapToStudent(StudentRequest studentRequest) {
        new Student();
        return Student.builder()
                .name(studentRequest.name())
                .user(userRepository.findById(studentRequest.userId()).orElseThrow(()-> new ResourceNotFoundException("user not found")))
                .build();


    }
}
