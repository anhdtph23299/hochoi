package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class LoginController {

    @Autowired
    private MessageSource messageSource;

    @PostMapping("helloworld")
    public String helloWorld(@RequestParam("number")String number, @RequestBody User user){
        return "Hello "+Integer.parseInt(number)+user;
    }


    @GetMapping("exception")
    public String exception(@RequestParam("name")String name) throws Exception {
        throw new RuntimeException("Exception ở đây"+name);
    }

    @GetMapping(path = "/hello-world-i18n")
    public String helloWorld() {
        return messageSource.getMessage("good.morning.message", null, Locale.US);
    }

}
