package com.unais.lms.service;

import com.unais.lms.dto.CourseRequest;
import com.unais.lms.dto.CourseResponse;
import com.unais.lms.exception.ResourceNotFoundException;
import com.unais.lms.mappers.CourseMapper;
import com.unais.lms.model.Course;
import com.unais.lms.repo.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {


    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }


    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::mapToCourseResponse)
                .toList();
    }

    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return courseMapper.mapToCourseResponse(course);
    }

    public CourseResponse createCourse(CourseRequest courseRequest) {
        Course createdCourse = courseRepository.save(courseMapper.mapToCourse(courseRequest));
        return courseMapper.mapToCourseResponse(createdCourse);
    }
    public CourseResponse updateCourse(Long id,CourseRequest courseRequest) {
        Course course=courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        course.setCourseName(courseRequest.courseName());
        course.setCourseCode(courseRequest.courseCode());
        course.setCourseDescription(courseRequest.courseDescription());
        courseRepository.save(course);
        return courseMapper.mapToCourseResponse(course);
    }


    public void deleteCourse(Long id) {
        Course course=courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        courseRepository.delete(course);
    }
}
