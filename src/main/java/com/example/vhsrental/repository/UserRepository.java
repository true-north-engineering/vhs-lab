package com.example.vhsrental.repository;

import com.example.vhsrental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
