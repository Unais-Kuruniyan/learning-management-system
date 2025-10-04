package com.unais.lms.mappers;

import com.unais.lms.dto.CourseRequest;
import com.unais.lms.dto.CourseResponse;
import com.unais.lms.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseResponse mapToCourseResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getCourseCode(),
                course.getCourseDescription()

        );

    }

    public Course mapToCourse(CourseRequest courseRequest) {
        new Course();
        return Course.builder()
                .courseName(courseRequest.courseName())
                .courseCode(courseRequest.courseCode())
                .courseDescription(courseRequest.courseDescription())
                .build();
    }
}
