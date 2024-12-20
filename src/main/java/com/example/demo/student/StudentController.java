package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student") // Defines base route
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id, @RequestBody Student student) {
        student.setId(id);
        studentService.updateStudent(student);
    }

    public static class StudentUpdatePayload {
        public String name;
        public String email;
    }

    @PutMapping(path = "alt/{studentId}")
    public void updateStudent2(
            @PathVariable("studentId") Long id,
            @RequestParam String name,
            @RequestParam String email) {
        studentService.updateStudent2(id, name, email);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }

}
