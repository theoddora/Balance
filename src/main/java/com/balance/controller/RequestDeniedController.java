package com.balance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hangelov on 30/11/2016.
 */
@Controller
public class RequestDeniedController {

    @RequestMapping("/denied")
    public String accessDenied() {
        return "404";
    }

}
