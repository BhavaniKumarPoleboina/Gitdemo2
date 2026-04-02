package com.coempt.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coempt.Entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByStudentId(Long studentId);
}
