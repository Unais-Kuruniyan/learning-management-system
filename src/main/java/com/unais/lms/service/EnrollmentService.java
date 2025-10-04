package com.unais.lms.service;

import com.unais.lms.dto.EnrollmentRequest;
import com.unais.lms.dto.EnrollmentResponse;
import com.unais.lms.exception.ResourceNotFoundException;
import com.unais.lms.mappers.CourseMapper;
import com.unais.lms.mappers.EnrollmentMapper;
import com.unais.lms.model.Enrollment;
import com.unais.lms.repo.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentService {


    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final CourseMapper courseMapper;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, EnrollmentMapper enrollmentMapper, CourseMapper courseMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
        this.courseMapper = courseMapper;
    }

    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(enrollmentMapper::mapToEnrollmentResponse)
                .toList();
    }

    public EnrollmentResponse getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
        return enrollmentMapper.mapToEnrollmentResponse(enrollment);

    }


    public EnrollmentResponse createEnrollment(EnrollmentRequest enrollmentRequest) {
        Enrollment enrollment = enrollmentRepository.save(enrollmentMapper.mapToEnrollment(enrollmentRequest));
        return enrollmentMapper.mapToEnrollmentResponse(enrollment);
    }
    public EnrollmentResponse updateEnrollment(Long id,EnrollmentRequest enrollmentRequest) {
        Enrollment enrollment=enrollmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
        enrollment.setEnrollmentDate(LocalDateTime.now());
        enrollment.setStudent(enrollment.getStudent());
        enrollment.setCourse(enrollmentRequest.course());
        enrollmentRepository.save(enrollment);
        return enrollmentMapper.mapToEnrollmentResponse(enrollment);

    }

    public void deleteEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
        enrollmentRepository.delete(enrollment);
    }

}
