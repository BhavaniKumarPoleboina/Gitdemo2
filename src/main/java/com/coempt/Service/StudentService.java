package com.coempt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coempt.Entity.Student;
import com.coempt.Repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student register(Student student) {
        return studentRepository.save(student);
    }

    public Student login(String email, String password) {
        Student student = studentRepository.findByEmail(email);
        return (student != null && student.getPassword().equals(password)) ? student : null;
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

}

