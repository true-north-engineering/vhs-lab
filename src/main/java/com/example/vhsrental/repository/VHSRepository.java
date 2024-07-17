package com.example.vhsrental.repository;

import com.example.vhsrental.entity.VHS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VHSRepository extends JpaRepository<VHS, Long> {
}
