package learning.security.secure_post.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learning.security.secure_post.config.TokenService;
import learning.security.secure_post.models.User;
import learning.security.secure_post.models.dto.RegisterDTO;
import learning.security.secure_post.models.dto.auth.AuthDTO;
import learning.security.secure_post.models.dto.auth.ResponseAuthDTO;
import learning.security.secure_post.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    final private AuthenticationManager authenticationManager;
    final private UserRepository userRepository;
    final private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(authDTO.login(), authDTO.password());
        var auth = authenticationManager.authenticate(authToken);
        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);
        return ResponseEntity.ok(new ResponseAuthDTO(user, token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        if (this.userRepository.findByLogin(registerDTO.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        var encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        var user = userRepository.save(new User(registerDTO.role(), registerDTO.login(), encryptedPassword));
        user.setPassword(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
