package net.Management001.Student_mgt_system_SpringBoot.Controller;

import jakarta.servlet.http.HttpServletResponse;
import net.Management001.Student_mgt_system_SpringBoot.DTO.LoginRequest;
import net.Management001.Student_mgt_system_SpringBoot.Entity.Teachers;
import net.Management001.Student_mgt_system_SpringBoot.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "http://localhost:5173")
public class TeachersController {

    @Autowired
    public TeacherService teacherService;
    @Autowired
    private HttpServletResponse httpServletResponse;

    @GetMapping
    public List<Teachers> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = teacherService.authenticateTeacher(loginRequest.getEmail(), loginRequest.getPassword());
        if (isAuthenticated) {
            return "Login successful!"; // You can replace this with proper response logic
        } else {
            throw new RuntimeException("Invalid email or password.");
        }
    }


    @GetMapping("/{id}")
    public Teachers getTeacherById(@PathVariable long id) {
        return teacherService.getTeacherById(id).orElseThrow(()->
                new RuntimeException("teacher not found at id " + id));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeachers(@RequestBody Teachers teachers) {
        teacherService.addTeachers(teachers);
    }


    @PutMapping("update/{id}")
    public Teachers updateTeachers(@PathVariable long id,@RequestBody Teachers updatedTeachers) {
        return teacherService.updateTeachers(id,updatedTeachers);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeachers(@PathVariable long id) {
        teacherService.deleteTeachers(id);
    }

}
