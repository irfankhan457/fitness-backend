package com.fitness.user;


import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       BCryptPasswordEncoder encoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

//    public void register(RegisterRequest request) {
//
//        Users user = new Users();
//        user.setEmail(request.getEmail());
//        user.setPassword(encoder.encode(request.getPassword()));
//        user.setRole("USER");
//
//        userRepository.save(user);
//    }
public Users register(Users request) {
    String email = request.getEmail() == null ? "" : request.getEmail().trim().toLowerCase();
    String password = request.getPassword() == null ? "" : request.getPassword().trim();

    if (email.isEmpty() || password.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email and password are required");
    }

    if (userRepository.existsByEmail(email)) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
    }

    Users user = new Users();
    user.setEmail(email);
    user.setPassword(encoder.encode(password));
    user.setRole(request.getRole() == null || request.getRole().isBlank() ? "USER" : request.getRole());

    return userRepository.save(user);
}

    public LoginResponse login(LoginRequest request) {
        String email = request.getEmail() == null ? "" : request.getEmail().trim();
        String rawPassword = request.getPassword() == null ? "" : request.getPassword().trim();

        if (email.isEmpty() || rawPassword.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email and password are required");
        }

        Users user = userRepository
                .findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        String storedPassword = user.getPassword() == null ? "" : user.getPassword();
        boolean bcryptMatch = storedPassword.startsWith("$2") && encoder.matches(rawPassword, storedPassword);
        boolean plainTextMatch = storedPassword.equals(rawPassword);

        if (!bcryptMatch && !plainTextMatch) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        if (plainTextMatch && !bcryptMatch) {
            user.setPassword(encoder.encode(rawPassword));
            userRepository.save(user);
        }

        String token = jwtUtil.generateToken(user.getEmail());
        String role = (user.getRole() == null || user.getRole().isBlank()) ? "USER" : user.getRole();
        return new LoginResponse(token, user.getId(), user.getEmail(), role);
    }
}