package com.coempt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coempt.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findByUsername(String username);
}
