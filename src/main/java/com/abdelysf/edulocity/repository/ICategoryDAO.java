package com.abdelysf.edulocity.repository;

import com.abdelysf.edulocity.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryDAO extends JpaRepository<Category,Integer> {

    Category findCategoryByCategoryName(String s);
}
