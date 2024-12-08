package net.Management001.Student_mgt_system_SpringBoot.Service;

import net.Management001.Student_mgt_system_SpringBoot.Entity.Teachers;
import net.Management001.Student_mgt_system_SpringBoot.Repository.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    TeachersRepository teachersRepository;

    // Retrieve all teachers
    public List<Teachers> getAllTeachers() {
        return teachersRepository.findAll();
    }

    // Retrieve a teacher by ID
    public Optional<Teachers> getTeacherById(long id) {
        return teachersRepository.findById(id);
    }

    // Add a new teacher
    public void addTeachers(Teachers teachers) {
        // Debugging: Log the teacher's fullname to ensure it's set correctly
        System.out.println("Adding teacher with fullname: " + teachers.getFullName());

        // Save teacher to the database
        teachersRepository.save(teachers);
    }

    // Update an existing teacher
    public Teachers updateTeachers(long id, Teachers updatedTeachers) {
        Teachers existingTeachers = teachersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        // Update fields
        existingTeachers.setFullName(updatedTeachers.getFullName());
        existingTeachers.setEmail(updatedTeachers.getEmail());
        existingTeachers.setPhone(updatedTeachers.getPhone());
        existingTeachers.setPassword(updatedTeachers.getPassword());

        // Save updated teacher to the database
        teachersRepository.save(existingTeachers);

        return existingTeachers;
    }

    // Delete a teacher
    public void deleteTeachers(long id) {
        teachersRepository.deleteById(id);
    }
    // Authenticate a teacher by email and password

    public boolean authenticateTeacher(String email, String password) {
        Optional<Teachers> teacher = teachersRepository.findByEmail(email);
        if (teacher.isPresent() && teacher.get().getPassword().equals(password)) {
            return true; // Credentials match
        }
        return false; // Credentials do not match
    }


}
