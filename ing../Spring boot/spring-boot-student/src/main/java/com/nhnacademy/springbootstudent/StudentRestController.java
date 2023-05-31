package com.nhnacademy.springbootstudent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class StudentRestController {

    private final StudentService studentService;

    @GetMapping("/web/students/{id}")
    public String getStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", new Student(3L, "김화정", 100));
        return "student";
    }
}
