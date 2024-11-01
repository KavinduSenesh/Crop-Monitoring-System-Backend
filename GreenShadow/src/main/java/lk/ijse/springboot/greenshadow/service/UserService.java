package lk.ijse.springboot.greenshadow.service;


import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lk.ijse.springboot.greenshadow.dto.UserDTO;

public interface UserService {
    void saveUser(UserDTO userDTO);
    UserResponse getUserByEmail(String email);
    void updateUser(UserDTO userDTO);

}
