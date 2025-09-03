package com.wipro.sindhu.authservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wipro.sindhu.authservice.enums.UserRole;
import com.wipro.sindhu.authservice.services.jwt.UserService;

import lombok.RequiredArgsConstructor;

/*
 * 
 * @PreAuthorize("hasRole('ADMIN')")

@Secured("ROLE_USER")

@RolesAllowed("ADMIN")
 */
	
	@Configuration  
	@EnableWebSecurity
	@EnableMethodSecurity
	@RequiredArgsConstructor
	public class WebSecurityConfiguration 
	{
		@Autowired
		private JwtAuthenticationFilter jwtAuthenticationFilter;
	    @Autowired
		private UserService userService; // Make final for @RequiredArgsConstructor

	    @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http.csrf(AbstractHttpConfigurer::disable)
	                .authorizeHttpRequests(request ->
	                        request.requestMatchers("/api/auth/**", "/actuator/**").permitAll()
	                               .requestMatchers("/api/admin/**").hasAuthority(UserRole.ADMIN.name())
	                               .requestMatchers("/api/patient/**").hasAuthority(UserRole.PATIENT.name())
	                               .requestMatchers("/api/doctor/**").hasAuthority(UserRole.DOCTOR.name())
	                               .anyRequest().authenticated())
	                .sessionManagement(session ->
	                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .authenticationProvider(authenticationProvider())
	                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
	                .httpBasic(Customizer.withDefaults())
	                .build();
	    }

	    @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userService.userDetailsService()); // correct
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	    }

	    @Bean
	    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	}
		
	
