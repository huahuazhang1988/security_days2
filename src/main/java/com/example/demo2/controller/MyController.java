package com.example.demo2.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
//    @GetMapping("/goto")
//    public String geoto(){
//        return "redirect:main.html";
//    }
    //@Secured("ROLE_abc")
   //("hasRole('uuh')")
    @RequestMapping ("/toMain")

    public String toMain(){
        return "redirect:main.html";
    }

    @RequestMapping ("/toError")
    public String toError(){
        return "redirect:error.html";
    }


    @GetMapping("/demo")
    public String demo(){
        return "demo";
    }
    @GetMapping("/showLogin")
    public String showLogin(){
        return "login";
    }
}
