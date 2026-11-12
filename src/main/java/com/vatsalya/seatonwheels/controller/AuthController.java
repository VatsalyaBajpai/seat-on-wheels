package com.vatsalya.seatonwheels.controller;

import com.vatsalya.seatonwheels.dto.AuthResponse;
import com.vatsalya.seatonwheels.dto.LoginRequest;
import com.vatsalya.seatonwheels.dto.RegisterRequest;
import com.vatsalya.seatonwheels.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}