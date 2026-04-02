package com.coempt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coempt.Entity.Course;
import com.coempt.Entity.Registration;
import com.coempt.Entity.Student;
import com.coempt.Service.CourseService;
import com.coempt.Service.RegistrationService;
import com.coempt.Service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentservice;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        Student byId = studentservice.getById(student.getId());
        System.out.println(byId + "==============================");
        model.addAttribute("student", byId);
        List<Registration> registrations = registrationService.getRegistrationsByStudent(student.getId());
        model.addAttribute("registrations", registrations);
        model.addAttribute("courses", courseService.getAllCourses());
        return "student_dashboard";
    }

    @PostMapping("/register")
    public String registerCourse(@RequestParam("studentId") Long studentId,
                                 @RequestParam("courseId") Long courseId,
                                 RedirectAttributes redirectAttributes) {
        try {
            // Check if the student is already registered for the course
            if (registrationService.isStudentAlreadyRegistered(studentId, courseId)) {
                redirectAttributes.addFlashAttribute("error", "You have already registered for this course.");
                return "redirect:/student/dashboard";
            }

            Course course = courseService.getCourseById(courseId);
            Student student = studentservice.getById(studentId);
            registrationService.registerStudentToCourse(student, course);
            redirectAttributes.addFlashAttribute("success", "Course registered successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
        }
        return "redirect:/student/dashboard";
    }
    
    @GetMapping("/delete")
    public String deleteRegistration(@RequestParam Long registrationId, RedirectAttributes redirectAttributes) {
        try {
            registrationService.deleteById(registrationId);
            redirectAttributes.addFlashAttribute("success", "Course deregistered successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete registration.");
        }
        return "redirect:/student/dashboard";
    }

    @GetMapping("/profile")
    public String viewProfile(@RequestParam("id") Long studentId, Model model) {
        Student student = studentservice.getById(studentId);
        model.addAttribute("student", student);
        return "edit"; // loads profile.html
    }
    @PostMapping("/update")
    public String updateProfile(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        studentservice.register(student); // your service should handle save/update logic
        redirectAttributes.addFlashAttribute("success", "Profile updated successfully.");
        return "redirect:/student/dashboard";
    }

}
