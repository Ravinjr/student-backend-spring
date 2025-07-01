package edu.icet.controller;

import edu.icet.entity.SystemUser;
import edu.icet.repository.UserRepository;
import edu.icet.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register/user")
    public ResponseEntity<StandardResponse> registerUser(@RequestBody SystemUser systemUser){
//        systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
//        return userRepository.save(systemUser);
        systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        SystemUser user = userRepository.save(systemUser);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",user.getPassword()),
                HttpStatus.CREATED
        );
    }
}
