package com.example.ksiazkiwypozyczalnia.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return "user";
    }
    @GetMapping("/")
    public String home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"))) {
            return "admin";
        }
        else if(auth.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_USER"))){
            return "log_user";
        }
        else {
                return "user";
            }
        }
}
