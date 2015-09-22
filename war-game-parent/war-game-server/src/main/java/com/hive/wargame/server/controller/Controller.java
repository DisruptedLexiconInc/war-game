package com.hive.wargame.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(value = "/hello")
    public String getName() {
        return "Hello";
    }

}
