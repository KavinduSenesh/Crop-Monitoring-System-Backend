package lk.ijse.springboot.greenshadow.controller;

import jakarta.validation.Valid;
import lk.ijse.springboot.greenshadow.dto.impl.UserDTO;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.jwtModels.JWTAuthResponse;
import lk.ijse.springboot.greenshadow.jwtModels.SignIn;
import lk.ijse.springboot.greenshadow.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signUp(@Valid @RequestBody UserDTO userDTO){
        logger.info("User sign up request received from a email");
        try{
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            JWTAuthResponse jwtAuthResponse = authenticationService.signUp(userDTO);
            if (jwtAuthResponse != null){
                return new ResponseEntity<>(jwtAuthResponse,HttpStatus.CREATED);
            }else {
                logger.warn("Failed to sign up user ");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (DataPersistFailedException e){
            logger.error("Failed to persist user", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error("Failed to persist user", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "signin")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn){
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }

    @PostMapping("refresh")
    public ResponseEntity<JWTAuthResponse> refreshToken(@RequestParam("refreshToken") String refreshToken){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }

}
