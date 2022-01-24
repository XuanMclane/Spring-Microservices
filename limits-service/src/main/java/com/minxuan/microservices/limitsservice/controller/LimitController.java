package com.minxuan.microservices.limitsservice.controller;

import com.minxuan.microservices.limitsservice.bean.Limits;
import com.minxuan.microservices.limitsservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {
    private final Configuration configuration;

    @Autowired
    public LimitController(Configuration configuration) {
        this.configuration = configuration;
    }


    @GetMapping("/limits")
    public Limits retriveLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
        //return new Limits(1,1000);
    }
}
