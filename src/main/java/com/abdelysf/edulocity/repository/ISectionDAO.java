package com.abdelysf.edulocity.repository;


import com.abdelysf.edulocity.model.Course;
import com.abdelysf.edulocity.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISectionDAO extends JpaRepository<Section,Long> {

    public List<Section> findAllByCourse(Course course);
}
