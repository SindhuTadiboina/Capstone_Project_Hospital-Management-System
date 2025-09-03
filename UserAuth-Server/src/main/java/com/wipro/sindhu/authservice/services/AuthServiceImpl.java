package com.wipro.sindhu.authservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wipro.sindhu.authservice.dto.SignupRequest;
import com.wipro.sindhu.authservice.dto.UserDto;
import com.wipro.sindhu.authservice.entities.User;
import com.wipro.sindhu.authservice.enums.UserRole;
import com.wipro.sindhu.authservice.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
    private UserRepository userRepository;        // final for constructor injection
    @Autowired
    
	private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdminAccount() {
        Optional<User> optionalUser = userRepository.findByUserRole(UserRole.ADMIN);
        if (optionalUser.isEmpty()) {
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDto signupUser(SignupRequest signupRequest) {
        if (userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already exists");
        }

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.PATIENT);

        userRepository.save(user);
        return user.getUserDto();
    }

    @Transactional(rollbackFor = Exception.class)
    public UserDto signupDoctor(SignupRequest signupRequest) {
        if (userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already exists");
        }

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.DOCTOR);

        userRepository.save(user);
        return user.getUserDto();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
