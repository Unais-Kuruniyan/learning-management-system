package com.unais.lms.service;

import com.unais.lms.model.Enrollment;
import com.unais.lms.repo.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return  enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        return  enrollmentRepository.findById(id).get();
    }
}
