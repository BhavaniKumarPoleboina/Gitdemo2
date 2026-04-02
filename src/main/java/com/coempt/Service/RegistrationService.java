package com.coempt.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coempt.Entity.Course;
import com.coempt.Entity.Registration;
import com.coempt.Entity.Student;
import com.coempt.Repository.RegistrationRepository;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Registration> getRegistrationsByStudent(Long studentId) {
        return registrationRepository.findByStudentId(studentId);
    }

    public Registration registerCourse(Registration registration) {
        return registrationRepository.save(registration);
    }

	public void registerStudentToCourse(Student student, Course course) {
		Registration registration = new Registration();
        registration.setStudent(student);
        registration.setCourse(course);
        registration.setRegistrationDate(Date.valueOf(LocalDate.now()));

        registrationRepository.save(registration);
		
	}
	// Method to check if the student is already registered for the course
    public boolean isStudentAlreadyRegistered(Long studentId, Long courseId) {
        List<Registration> registrations = registrationRepository.findByStudentId(studentId);
        for (Registration registration : registrations) {
            if (registration.getCourse().getId().equals(courseId)) {
                return true; // Already registered
            }
        }
        return false; // Not registered
    }

	public void deleteById(Long registrationId) {
		registrationRepository.deleteById(registrationId);
		
	}
}