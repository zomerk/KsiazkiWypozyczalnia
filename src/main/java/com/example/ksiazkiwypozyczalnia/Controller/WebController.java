package com.example.ksiazkiwypozyczalnia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController{
    @GetMapping("/login")
    public String viewLoginPage() {
        // custom logic before showing login page...
        return "login";
    }
    @GetMapping("/home")
    public String viewUser() {
        // custom logic before showing login page...
        return "user.html";
    }
}
