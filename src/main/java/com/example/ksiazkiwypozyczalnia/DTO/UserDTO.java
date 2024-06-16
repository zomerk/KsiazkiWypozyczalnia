package com.example.ksiazkiwypozyczalnia.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Username cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must be at least 6 characters long, contain at least one uppercase letter and one digit")
    private String password;

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
