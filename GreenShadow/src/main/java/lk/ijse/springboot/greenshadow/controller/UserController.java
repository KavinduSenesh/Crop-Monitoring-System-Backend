package lk.ijse.springboot.greenshadow.controller;

import lk.ijse.springboot.greenshadow.dto.UserDTO;
import lk.ijse.springboot.greenshadow.exception.AlreadyExistsException;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.NotFoundException;
import lk.ijse.springboot.greenshadow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?>saveUser(@RequestBody UserDTO userDTO) {
        logger.info("Attempting to save user: {}", userDTO);
        try {
            userService.saveUser(userDTO);
            logger.info("Successfully saved user with email: {}", userDTO.getEmail());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            logger.error("Failed to save user: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataPersistFailedException e) {
            logger.error("Failed to save user: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<?>getUserByEmail(@PathVariable String email){
        logger.info("Fetching user with email: {}", email);
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?>updateUser(@RequestBody UserDTO userDTO){
        logger.info("Attempting to update user: {}", userDTO);
        try{
            userService.updateUser(userDTO);
            logger.info("Successfully updated user with email: {}", userDTO.getEmail());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            logger.error("Failed to update user: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
