package com.abdelysf.edulocity.repository;

import com.abdelysf.edulocity.model.Category;
import com.abdelysf.edulocity.model.Course;
import com.abdelysf.edulocity.model.Instructor;
import com.abdelysf.edulocity.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICourseDAO extends JpaRepository<Course,Integer> {



    List<Course> findCourseByInstructor(Instructor instructor);
    List<Course> findCourseByStudents(Student s);
    Optional<Course> findCourseById(int id);
    List<Course> findCourseByCategory(Category category);
    List<Course> findAll();

    @Query(value = "SELECT c FROM Course c ")
    Page<Course> findCourse(PageRequest pageRequest);
    @Query(value = "SELECT c FROM Course c ")
    List<Course> findCourse();
    //@Query("SELECT c FROM Course c where c.approved =0")
    //List<Course> findCourseByApproved();

}
