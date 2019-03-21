package com.bh.workouts.bhworkoutapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
public class LoginController {

    private Logger logger = Logger.getLogger(LoginController.class.getName());

    @RequestMapping({"/", "/login"})
    public String login() {

        logger.info("=======================");
        logger.info("Getting Login page");

        return "login";
    }


}
