package edu.icet.controller;

import edu.icet.dto.UserDTO;
import edu.icet.entity.SystemUser;
import edu.icet.repository.UserRepository;
import edu.icet.service.UserService;
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
    private final UserService userService;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/register/user")
    public ResponseEntity<StandardResponse> registerUser(@RequestBody SystemUser systemUser){
        systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        SystemUser user = userRepository.save(systemUser);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",user.getPassword()),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<StandardResponse> signUp(@RequestBody UserDTO userDTO){
        SystemUser user = userService.save(userDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"User Registered",user.getUsername()),
                HttpStatus.CREATED
        );
    }
}
