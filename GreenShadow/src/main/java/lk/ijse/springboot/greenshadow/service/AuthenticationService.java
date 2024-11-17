package lk.ijse.springboot.greenshadow.service;

import lk.ijse.springboot.greenshadow.dto.impl.UserDTO;
import lk.ijse.springboot.greenshadow.jwtModels.JWTAuthResponse;
import lk.ijse.springboot.greenshadow.jwtModels.SignIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface AuthenticationService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDTO signUp);
    JWTAuthResponse refreshToken(String accessToken);
}
