package com.coempt.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coempt.Entity.Course;
import com.coempt.Repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Save a new or existing course
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    // Get a course by its ID
    public Course getCourseById(Long id) {
        Course Course = courseRepository.findById(id).get();
        return Course;  // Return null if not found
    }

    // Delete a course by its ID
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
    
    public List<Course> searchCourses(String keyword) {
        return courseRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
