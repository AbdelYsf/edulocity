package com.abdelysf.edulocity.repository;


import com.abdelysf.edulocity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IUserDAO extends JpaRepository<User,Long> {

    public Optional<User> findByUserName(String userName);

}
