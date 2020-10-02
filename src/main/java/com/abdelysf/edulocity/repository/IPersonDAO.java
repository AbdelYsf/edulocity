package com.abdelysf.edulocity.repository;


import com.abdelysf.edulocity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPersonDAO extends JpaRepository<User,Long> {

    public User findPersonByUsername(String userName);
}
