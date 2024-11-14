package lk.ijse.springboot.greenshadow.service.impl;

import lk.ijse.springboot.greenshadow.customObj.impl.UserErrorResponse;
import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lk.ijse.springboot.greenshadow.dto.impl.UserDTO;
import lk.ijse.springboot.greenshadow.entity.User;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.repository.UserRepository;
import lk.ijse.springboot.greenshadow.service.UserService;
import lk.ijse.springboot.greenshadow.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO) {
        Optional<User> isExistsUser = userRepository.findByEmail(userDTO.getEmail());
        if (!isExistsUser.isPresent()){
            User save = userRepository.save(mapping.convertUserDTOToUser(userDTO));
            if (save == null){
                throw new DataPersistFailedException("User save failed");
            }
        }else {
            throw new DataPersistFailedException("User already exists..");
        }
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        Optional<User> existsUser = userRepository.findByEmail(email);
        if (existsUser.isPresent()){
            return mapping.convertUserToUserDTO(existsUser.get());
        }else {
            return new UserErrorResponse(0,"User not found");
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<User> existsUser = userRepository.findByEmail(userDTO.getEmail());
        if (existsUser.isPresent()){
            existsUser.get().setPassword(userDTO.getPassword());
        }else {
//            throw new NotFoundException("User not exists");
        }
    }
}
