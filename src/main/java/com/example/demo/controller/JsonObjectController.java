package com.example.demo.controller;

import com.example.demo.service.JsonObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json")
public class JsonObjectController {
    @Autowired
    private JsonObjectService jsonObjectService;

    @GetMapping("/json")
    public String getJson() {
        return jsonObjectService.createJsonObject();
    }
}
