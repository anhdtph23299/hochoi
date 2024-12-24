package com.example.demo.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logging")
public class LoggingController {

    Logger logger =org.slf4j.LoggerFactory.getLogger(LoggingController.class);
    @GetMapping
    public String log() {
        logger.info("Logging info message");
        return "ok";
    }
}
