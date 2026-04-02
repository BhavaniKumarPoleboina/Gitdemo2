package com.coempt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coempt.Entity.Admin;
import com.coempt.Repository.AdminRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin login(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        return (admin != null && admin.getPassword().equals(password)) ? admin : null;
    }
}