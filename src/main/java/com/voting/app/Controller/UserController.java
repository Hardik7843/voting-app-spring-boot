package com.voting.app.Controller;

import com.voting.app.Entities.User;
import com.voting.app.JwtService;
import com.voting.app.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/profile/{id:[0-9]+}")
    public User getUserProfile(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody @Valid User user) {
        User u = userService.loginUser(user);
        String jwtToken = jwtService.generateToken(u.getEmail());


        ResponseCookie cookie = ResponseCookie.from("access_token", jwtToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ofHours(1))
                .sameSite("Strict")
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserSession(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).body("Not authenticated");
        }

        User user = (User) auth.getPrincipal(); // exact same object fetched in the filter

        return ResponseEntity.status(200).body(user);

    }

}
