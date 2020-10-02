package com.abdelysf.edulocity.repository;


import com.abdelysf.edulocity.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPartDAO extends JpaRepository<Part,Long> {
}
