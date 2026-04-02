package com.coempt.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coempt.Entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	List<Course> findByTitleContainingIgnoreCase(String keyword);

}
