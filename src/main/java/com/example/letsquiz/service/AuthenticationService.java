package com.example.letsquiz.service;

import com.example.letsquiz.entity.user.AppUser;
import com.example.letsquiz.entity.user.UserRole;
import com.example.letsquiz.requests.auth.AuthenticationRequest;
import com.example.letsquiz.responses.AuthenticationResponse;
import com.example.letsquiz.requests.auth.RegisterRequest;
import com.example.letsquiz.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest){
        boolean isUsernameExist = appUserService.existsByUsername(registerRequest.getUsername());
        boolean isEmailExist = appUserService.existsByEmail(registerRequest.getEmail());
        if(isUsernameExist || isEmailExist){
            throw new IllegalStateException("User already exist");
        }
        AppUser newUser = AppUser.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(UserRole.USER)
                .build();
        appUserService.save(newUser);
        String jwtToken = jwtService.generateToken(newUser);
        Date expiresAt = jwtService.extractExpiration(jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).expiresAt(expiresAt).hasAvatar(false).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        AppUser user =  appUserService.findUserByUsername(authenticationRequest.getUsername());
        String jwtToken = jwtService.generateToken(user);
        Date expiresAt = jwtService.extractExpiration(jwtToken);
        boolean hasAvatar = Objects.nonNull(user.getAvatar());
        return AuthenticationResponse.builder().token(jwtToken).expiresAt(expiresAt).hasAvatar(hasAvatar).build();
    }
}
