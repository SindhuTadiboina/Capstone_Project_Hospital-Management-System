package com.wipro.sindhu.authservice.services;

import com.wipro.sindhu.authservice.dto.SignupRequest;
import com.wipro.sindhu.authservice.dto.UserDto;

public interface AuthService 
{

	// Signup a patient
    UserDto signupUser(SignupRequest signupRequest);

    // Signup a doctor
    UserDto signupDoctor(SignupRequest signupRequest);

    // Check if user exists by email
    boolean hasUserWithEmail(String email);
}
