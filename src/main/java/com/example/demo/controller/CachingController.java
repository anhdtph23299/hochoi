package com.example.demo.controller;

import com.example.demo.util.caching.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CachingController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<String> getStudents(@RequestParam(name = "name")String name) {
        return studentService.getStudents(name);
    }
}
