package com.example.ksiazkiwypozyczalnia.Rest;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudUser;
import com.example.ksiazkiwypozyczalnia.DTO.UserDTO;
import com.example.ksiazkiwypozyczalnia.Service.UserService;
import com.example.ksiazkiwypozyczalnia.repo.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@RestController
@RequestMapping("/user/api")
public class UserAPI {
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.CreateUser(userDTO.getUsername(),userDTO.getPassword());
    }
    @GetMapping("/UserName")
    public ResponseEntity<?> getUserName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(auth.getName());
    }
    @GetMapping("/articles")
    public ResponseEntity<?> getArticles(){
        return ResponseEntity.ok(userService.wypozyczenieCzasopismaList());
    }
}
