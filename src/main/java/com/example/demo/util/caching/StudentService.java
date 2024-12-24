package com.example.demo.util.caching;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Cacheable(value = "students", key = "#name")
    public List<String> getStudents(String name) {
        try {
            // Giả lập độ trễ để cho thấy cache có tác dụng
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>() {{
            add("Student 1 " + name);
            add("Student 2 " + name);
            add("Student 3 " + name);
        }};
    }
}