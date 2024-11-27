package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
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

    @Transactional // TODO - what does?
    public void updateStudent(Student student) {
        Optional<Student> existingStudentOptional = studentRepository.findById(student.getId());

        if (existingStudentOptional.isEmpty()) {
            throwStudentDoesNotExist(student.getId());
        }

        Student existingStudent = existingStudentOptional.get();

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());

        studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throwStudentDoesNotExist(id);
        }

        studentRepository.deleteById(id);
    }

    private void throwStudentDoesNotExist(Long id) {
        throw new IllegalStateException("Student with this id %d' does not exist".formatted(id));
    }
}
