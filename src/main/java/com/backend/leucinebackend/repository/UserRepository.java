package com.backend.leucinebackend.repository;

import com.backend.leucinebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    List<User> findByRole(String role);
  User findByEmail(String email);
}
