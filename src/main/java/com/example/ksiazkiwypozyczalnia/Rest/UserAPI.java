package com.example.ksiazkiwypozyczalnia.Rest;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudUser;
import com.example.ksiazkiwypozyczalnia.DTO.UserDTO;
import com.example.ksiazkiwypozyczalnia.Service.UserService;
import com.example.ksiazkiwypozyczalnia.repo.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

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



}
