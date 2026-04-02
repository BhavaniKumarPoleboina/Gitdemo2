package com.coempt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coempt.Entity.Student;
import com.coempt.Service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	@Autowired
	private StudentService studentService;

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
		
		
		if(email.equals("admin@gmail.com") && password.equals("admin")) {
		    return "redirect:/admin/dashboard";
		}
		else {
			
			Student student = studentService.login(email, password);
			if(student!=null) {
			session.setAttribute("student", student);
			
			System.out.println(student);
			return "redirect:/student/dashboard";
			}
		}
		return "login";
	}

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("student", new Student());
		return "register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute Student student) {
		studentService.register(student);
		return "redirect:/login";
	}
}