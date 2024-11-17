package lk.ijse.springboot.greenshadow.service.impl;

import lk.ijse.springboot.greenshadow.dto.impl.UserDTO;
import lk.ijse.springboot.greenshadow.jwtModels.JWTAuthResponse;
import lk.ijse.springboot.greenshadow.jwtModels.SignIn;
import lk.ijse.springboot.greenshadow.repository.UserRepository;
import lk.ijse.springboot.greenshadow.service.AuthenticationService;
import lk.ijse.springboot.greenshadow.service.JWTService;
import lk.ijse.springboot.greenshadow.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final Mapping mapping;
    private final AuthenticationManager authenticationManager;

    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        var userByEmail = userRepository.findByEmail(signIn.getEmail())
                .orElseThrow(() ->new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(userByEmail);
        return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse signUp(UserDTO signUp) {
        var savedUser = userRepository.save(mapping.convertUserDTOToUser(signUp));
        try {
            var genToken = jwtService.generateToken(savedUser);
            return JWTAuthResponse.builder().token(genToken).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        var userName = jwtService.extractUserName(accessToken);
        var userEntity = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(userEntity);
        return JWTAuthResponse.builder().token(refreshToken).build();
    }
}
