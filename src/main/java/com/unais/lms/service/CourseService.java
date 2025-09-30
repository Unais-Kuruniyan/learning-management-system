package com.unais.lms.service;

import com.unais.lms.model.Course;
import com.unais.lms.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public List<Course> getAllCourses() {
            return  courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }
}
