package com.voting.app.Users;

import java.time.Duration;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voting.app.JwtService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/profile/{id}")
    public User getUserProfile(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody User user) {
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
        Cookie[] cookies = request.getCookies();

        if(cookies.length == 0){
            return ResponseEntity.status(401).body("No Cookies Found");
        }

        String token = cookies[0].getValue();

        String subject = jwtService.isTokenValid(token);

        User user = userService.getUserByEmail(subject);

        return ResponseEntity.status(200).body(user);

    }

}
