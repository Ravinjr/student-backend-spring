package edu.icet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id",length = 10)
    private Long courseId;

    @Column(name = "course_name",nullable = false)
    private String name;

    @Column(name = "course_code", nullable = false,length = 10,unique = true)
    private String code;

    @Column(name = "course_fee")
    private double fee;

    @Column(name = "duration")
    private String duration;
}
