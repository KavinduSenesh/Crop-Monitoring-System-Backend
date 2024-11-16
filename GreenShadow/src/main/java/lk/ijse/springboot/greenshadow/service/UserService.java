package lk.ijse.springboot.greenshadow.service;


import jakarta.validation.Valid;
import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lk.ijse.springboot.greenshadow.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(@Valid UserDTO userDTO);
    UserResponse getUserByEmail(String email);
    void updateUser(@Valid UserDTO userDTO);

    UserDetailsService userDetailsService();
//    void deleteUser(String email);
//    List getAllUsers();
}
