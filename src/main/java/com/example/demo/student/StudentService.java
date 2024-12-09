package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentWithEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentWithEmail.isPresent()) {
            throw new IllegalStateException("A student with this email already exists");
        }

        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Student student) {
        Optional<Student> existingStudentOptional = studentRepository.findById(student.getId());

        if (existingStudentOptional.isEmpty()) {
            createStudentDoesNotExistException(student.getId());
        }

        Student existingStudent = existingStudentOptional.get();

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());

        studentRepository.save(existingStudent);
    }

    @Transactional
    public void updateStudent2(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(() -> createStudentDoesNotExistException(id));

        if (name != null && !name.isEmpty()) {
            student.setName(name);
        }

        if (email != null && !email.isEmpty()) {
            if (studentRepository.findStudentByEmail(email).isPresent()) {
                throw new IllegalStateException("A student with this email already exists");
            }
            student.setEmail(email);
        }
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw createStudentDoesNotExistException(id);
        }

        studentRepository.deleteById(id);
    }

    private RuntimeException createStudentDoesNotExistException(Long id) {
        return new IllegalStateException("Student with this id %d' does not exist".formatted(id));
    }
}
