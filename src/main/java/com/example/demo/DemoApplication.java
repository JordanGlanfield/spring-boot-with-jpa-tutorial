package com.example.demo;

import com.example.demo.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController // Marks class as a server of REST endpoints
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@GetMapping // Defines this as a GET endpoint
//	public String hello() {
//		return "Waguan fam?";
//	}

	@GetMapping
	public List<Student> allTheHello() {
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
