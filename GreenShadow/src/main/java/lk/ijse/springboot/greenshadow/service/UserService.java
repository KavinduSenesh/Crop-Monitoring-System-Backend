package lk.ijse.springboot.greenshadow.service;


import jakarta.validation.Valid;
import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lk.ijse.springboot.greenshadow.dto.impl.UserDTO;

public interface UserService {
    void saveUser(@Valid UserDTO userDTO);
    UserResponse getUserByEmail(String email);
    void updateUser(@Valid UserDTO userDTO);

}
