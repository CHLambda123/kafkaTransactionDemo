package com.lambda.bigtask.controller;

import com.lambda.bigtask.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private MyService myService;
    @GetMapping("/kafka")
    public String getKafkaTaskExecTime() {
        return myService.getKafkaTime();
    }
}
