package com.abdelysf.edulocity.repository;


import com.abdelysf.edulocity.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentDAO extends JpaRepository<Student,Long> {
}
