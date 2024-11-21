package com.example.demo.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "Edward",
                        "edward.davis@test.com",
                        LocalDate.of(1990, Month.APRIL, 11),
                        34
                )
        );
    }
}
