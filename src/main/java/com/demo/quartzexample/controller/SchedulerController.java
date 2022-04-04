package com.demo.quartzexample.controller;

import com.demo.quartzexample.quartz.schedulers.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class SchedulerController {

    private final SchedulerService service;

    @Autowired
    public SchedulerController(SchedulerService service) {
        this.service = service;
    }

    @PostMapping("/run-hello")
    public void runHello(){
        service.runHelloService();
    }
}
