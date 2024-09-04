package com.example.esspi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.esspi.model.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "BEGIN create_user(:username, :password); END;", nativeQuery = true)
    void createUser(String username, String password);

}