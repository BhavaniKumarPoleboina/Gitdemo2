package com.coempt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coempt.Entity.Course;
import com.coempt.Service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {
    
    @Autowired
    private CourseService courseService;

    // Display list of courses
    @GetMapping("")
    public String courseList(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";  // The template that displays the course list
    }

    // Show the form for adding a new course
    @GetMapping("/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "add_course"; 
        // The template for adding a new course
    }

    // Handle adding a new course
    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course) {
        courseService.save(course);
        return "redirect:/admin/dashboard";  // Redirect to dashboard after adding course
    }

    // Show the form to edit an existing course
    @GetMapping("/edit/{id}")
    public String showEditCourseForm(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "edit_course";  // The template for editing the course
    }

    // Handle updating an existing course
    @PostMapping("/edit/{id}")
    public String updateCourse(@PathVariable("id") Long id, @ModelAttribute Course course) {
        course.setId(id);
        courseService.save(course);
        return "redirect:/admin/dashboard";  // Redirect to the course list after updating
    }

    // Handle deleting a course
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/admin/dashboard";  // Redirect to the course list after deletion
    }
    
    @GetMapping("/search")
    public String searchCourses(@RequestParam("keyword") String keyword, Model model) {
        List<Course> courses = courseService.searchCourses(keyword);
        model.addAttribute("courses", courses);
        return "admin_dashboard";
    }
}
