package com.fitness.user;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
//        return ResponseEntity.ok("User registered");
//    }
@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody Users request) {
    Users created = authService.register(request);
    return ResponseEntity.ok(Map.of(
            "id", created.getId(),
            "email", created.getEmail(),
            "role", created.getRole(),
            "message", "User registered"
    ));
}
}