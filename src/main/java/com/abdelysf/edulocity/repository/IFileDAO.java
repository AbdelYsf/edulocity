package com.abdelysf.edulocity.repository;


import com.abdelysf.edulocity.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileDAO extends JpaRepository<File,Long> {
}
