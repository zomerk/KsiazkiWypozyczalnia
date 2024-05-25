package com.example.ksiazkiwypozyczalnia.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Username cannot be empty")
    private String password;

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
