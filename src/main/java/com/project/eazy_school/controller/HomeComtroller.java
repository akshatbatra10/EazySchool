package com.project.eazy_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeComtroller {

    @RequestMapping(value={"", "/", "/home"})
    public String displayHome() {
        return "home.html";
    }

}
