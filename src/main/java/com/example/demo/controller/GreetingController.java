package com.example.demo.controller;

import com.example.demo.model.Login;
import com.example.demo.util.language.UtilLanguage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/api/greet/{message}")
    public String greet(@PathVariable String message) {
        return UtilLanguage.getLanguage(message);
    }
    @GetMapping("/api/hellouser/{username}")
    @Operation(summary = "Hello user",
            description = "Hello user by language ultil")
    public String helloUser(@PathVariable String username) {
        return UtilLanguage.getLanguageParameter("greeting.user",username);
    }
    @GetMapping("validate")
    public String validate(@RequestParam("name") String name){
        return name;
    }

    @PostMapping("login")
    public Login validateLoginUser(@RequestBody @Valid Login login){
        return login;
    }

    @GetMapping("test")
    public String index(){
        return "Hello World 2!";
    }
}