package lk.ijse.springboot.greenshadow.controller;

import lk.ijse.springboot.greenshadow.dto.UserDTO;
import lk.ijse.springboot.greenshadow.exception.AlreadyExistsException;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.NotFoundException;
import lk.ijse.springboot.greenshadow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?>saveUser(@RequestBody UserDTO userDTO) {
        try {
            userService.saveUser(userDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<?>getUserByEmail(@PathVariable String email){
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?>updateUser(@RequestBody UserDTO userDTO){
        try{
            userService.updateUser(userDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
