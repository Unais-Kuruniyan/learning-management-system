package com.unais.lms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String courseName;
    @Column(unique = true)
    private String courseCode;
    @Column(nullable = true)
    private String courseDescription;

    @OneToMany
    private List<Enrollment> enrollments;




}
