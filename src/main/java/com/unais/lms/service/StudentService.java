package com.unais.lms.service;

import com.unais.lms.dto.StudentRequest;
import com.unais.lms.dto.StudentResponse;
import com.unais.lms.dto.StudentSummary;
import com.unais.lms.exception.ResourceNotFoundException;
import com.unais.lms.mappers.StudentMapper;
import com.unais.lms.model.Student;
import com.unais.lms.repo.StudentRepository;
import com.unais.lms.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.studentMapper = studentMapper;
    }


    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll().stream()
                .map(studentMapper::mapToStudentResponse).toList();
    }

    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        return studentMapper.mapToStudentResponse(student);
    }

    public StudentSummary createStudent(StudentRequest studentRequest) {
        Student savedStudent= studentRepository.save(studentMapper.mapToStudent(studentRequest));
        return studentMapper.mapToStudentSummary(savedStudent);
    }



    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id).orElseThrow();

        student.setName(studentRequest.name());
        student.setUser(userRepository.findById(studentRequest.userId()).orElseThrow(()-> new ResourceNotFoundException("user not found")));
        studentRepository.save(student);
        return studentMapper.mapToStudentResponse(student);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student not found"));
        studentRepository.delete(student);

    }
}
