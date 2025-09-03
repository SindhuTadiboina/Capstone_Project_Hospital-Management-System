package com.wipro.sindhu.authservice.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.wipro.sindhu.authservice.dto.AuthenticationRequest;
import com.wipro.sindhu.authservice.dto.AuthenticationResponse;
import com.wipro.sindhu.authservice.dto.SignupRequest;
import com.wipro.sindhu.authservice.dto.UserDto;
import com.wipro.sindhu.authservice.entities.User;
import com.wipro.sindhu.authservice.repositories.UserRepository;
import com.wipro.sindhu.authservice.services.AuthService;
import com.wipro.sindhu.authservice.services.jwt.UserService;
import com.wipro.sindhu.authservice.utils.JwtUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;
    private UserRepository userRepository;
    private JwtUtil jwtUtil;
    private UserService userService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup/patient")
    public ResponseEntity<?> signupPatient(@Valid @RequestBody SignupRequest signupRequest) {
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("User already exists with this email");
        }
        UserDto createdUserDto = authService.signupUser(signupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @PostMapping("/signup/doctor")
    public ResponseEntity<?> signupDoctor(@Valid @RequestBody SignupRequest signupRequest) {
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Doctor already exists with this email");
        }
        UserDto createdUserDto = authService.signupDoctor(signupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    ));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password!");
        }

        final UserDetails userDetails = userService.userDetailsService()
                .loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(authenticationRequest.getEmail());
        final String jwtToken = jwtUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwtToken);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }
        return authenticationResponse;
    }
}
