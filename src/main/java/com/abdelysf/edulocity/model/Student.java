package com.abdelysf.edulocity.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Student  extends User {

    private  String cne;
    private  String major;
    private Institution college ;
    private String lastObtainedDegree;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_taken_courses",
            joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"))
    private Collection<Course> takenCourses;





}
