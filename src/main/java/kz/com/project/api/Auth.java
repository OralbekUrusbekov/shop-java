package kz.com.project.api;

import kz.com.project.Dto.UserDto;
import kz.com.project.config.JwtUtil;
import kz.com.project.model.User;
import kz.com.project.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Auth {
    private final @Lazy UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    /**
     * Registers a new user in the system.
     * Accepts user data (email, password, etc.) from the client
     * and saves the user after validation and password hashing.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        userService.register(userDto);
        return ResponseEntity.ok("User registered successfully");
    }

    /**
     * Authenticates user credentials (email and password).
     * If authentication is successful, generates and returns a JWT token.
     * If authentication fails, returns HTTP 401 Unauthorized.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.getEmail(), userDto.getPassword()
                    )
            );

            String token = jwtUtil.generateToken(
                    (User) userService.loadUserByUsername(userDto.getEmail())
            );

            return ResponseEntity.ok(token);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    /**
     * Returns all users.
     * Accessible only for authenticated users with USER or ADMIN authority.
     */
    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok("dfgb");
    }
}
