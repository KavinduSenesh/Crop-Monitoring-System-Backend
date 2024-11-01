package lk.ijse.springboot.greenshadow.dto;

import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDTO, UserResponse {
    private String email;
    private String password;
    private String role;
}
