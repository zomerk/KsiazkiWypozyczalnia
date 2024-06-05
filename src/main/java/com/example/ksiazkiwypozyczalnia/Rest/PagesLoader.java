package com.example.ksiazkiwypozyczalnia.Rest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PagesLoader {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage(){
        return "admin.html";
    }
}
