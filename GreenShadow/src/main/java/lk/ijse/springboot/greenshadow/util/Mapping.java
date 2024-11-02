package lk.ijse.springboot.greenshadow.util;

import lk.ijse.springboot.greenshadow.dto.UserDTO;
import lk.ijse.springboot.greenshadow.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper mapper;

    public User convertUserDTOToUser(UserDTO userDTO){
        return mapper.map(userDTO, User.class);
    }
    public UserDTO convertUserToUserDTO(User user){
        return mapper.map(user, UserDTO.class);
    }

}
