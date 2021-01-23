package com.sadheera.wileytest.controller;

import com.sadheera.wileytest.exception.BadRequestException;
import com.sadheera.wileytest.exception.UserNotVerifiedException;
import com.sadheera.wileytest.model.ConfirmationToken;
import com.sadheera.wileytest.model.User;
import com.sadheera.wileytest.payload.ApiResponse;
import com.sadheera.wileytest.payload.AuthResponse;
import com.sadheera.wileytest.payload.LoginRequest;
import com.sadheera.wileytest.payload.SignUpRequest;
import com.sadheera.wileytest.security.CustomUserDetailsService;
import com.sadheera.wileytest.security.TokenProvider;
import com.sadheera.wileytest.service.AuthService;
import com.sadheera.wileytest.service.EmailSenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Calendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        
        if (userDetailsService.isAccountVerified(user.getUsername()) == false) {
            throw new UserNotVerifiedException("Account is not Verified. Please check for confirmation email.");
        }
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        return ResponseEntity.ok(new AuthResponse(token, "Login Success"));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if (authService.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email Already Exists.");
        }

        User user = authService.saveUser(signUpRequest);
        ConfirmationToken confirmationToken = authService.createToken(user);
        emailSenderService.sendMail(user.getEmail(), confirmationToken.getConfirmationToken());

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Signup Successfully. Confirmation mail sent"));
    }

    @GetMapping("confirm-account")
    public ResponseEntity<?> getMethodName(@RequestParam("token") String token) {
        ConfirmationToken confirmationToken = authService.findByConfirmationToken(token);
        
        if (confirmationToken == null) {
            throw new BadRequestException("Invalid token");
        }

        User user = confirmationToken.getUser();
        Calendar calendar = Calendar.getInstance();

        if (user.getEmailVerified()) {
            throw new BadRequestException("Account Already Verified");
        }
        
        if((confirmationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
            ConfirmationToken newToken = authService.createToken(user);
            emailSenderService.sendMail(user.getEmail(), newToken.getConfirmationToken());
            return ResponseEntity.ok(new ApiResponse(true, "Token Expired. New confirmation mail sent. Please check Inbox"));
        }

        user.setEmailVerified(true);
        authService.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "Email Verified Successfully!"));
    }
}
