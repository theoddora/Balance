package com.balance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestDeniedController {

    @RequestMapping("/denied")
    public String accessDenied() {
        return "404";
    }

}
