package com.abdelysf.edulocity.repository;


import com.abdelysf.edulocity.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorDAO extends JpaRepository<Instructor,Long> {
}
