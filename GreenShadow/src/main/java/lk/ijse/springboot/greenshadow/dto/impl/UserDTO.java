package lk.ijse.springboot.greenshadow.dto.impl;

import jakarta.validation.constraints.*;
import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lk.ijse.springboot.greenshadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDTO, UserResponse {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;
    @NotNull
    @Pattern(regexp = "OTHER|MANAGER|ADMINISTRATIVE|SCIENTIST")
    private String role;
}
