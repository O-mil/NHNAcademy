package com.nhnacademy.springbootstudent;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/web/students/{id}")
    public String getStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", new Student(3L, "김화정", 100));
        return "student";
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "{\"result\":\"OK\"}";
    }
}
