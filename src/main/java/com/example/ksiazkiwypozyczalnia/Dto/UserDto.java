package com.example.ksiazkiwypozyczalnia.Dto;

import lombok.*;
import jakarta.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    @NonNull
    String username;
    @Size(min = 5)
    String password;
}
