package net.Management001.Student_mgt_system_SpringBoot.Service;

import net.Management001.Student_mgt_system_SpringBoot.Entity.Student;
import net.Management001.Student_mgt_system_SpringBoot.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(long id) {
        return studentRepository.findById(id);
    }


    public void addStudent(Student student) {
        studentRepository.save(student);
    }


    public Student updateStudent(long id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Student not found with ID: " + id));

        existingStudent.setFullName(updatedStudent.getFullName());
        existingStudent.setRegistrationNo(updatedStudent.getRegistrationNo());
        existingStudent.setDepartment(updatedStudent.getDepartment());
        existingStudent.setYearOfStudy(updatedStudent.getYearOfStudy());
        // Update other fields as necessary
        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }
}
