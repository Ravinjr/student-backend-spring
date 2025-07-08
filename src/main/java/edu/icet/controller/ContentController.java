package edu.icet.controller;

import edu.icet.dto.UserDTO;
import edu.icet.entity.SystemUserDetailsService;
import edu.icet.service.UserService;
import edu.icet.util.JwtUtil;
import edu.icet.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
//@RequestMapping("/api/v1/users")
public class ContentController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final SystemUserDetailsService systemUserDetailsService;

    public ContentController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, SystemUserDetailsService systemUserDetailsService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.systemUserDetailsService = systemUserDetailsService;
    }

    @GetMapping("/home")
    public String handleWelcome() {
        System.out.println("Welcome home");
        return "Welcome to home!";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome() {
        System.out.println("admin home");
        return "Welcome to ADMIN home!";
    }

    @GetMapping("/user/home")
    public String handleUserHome() {
        return "Welcome to USER home!";
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody UserDTO userDTO) {
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               userDTO.getUsername(),userDTO.getPassword()
       )) ;
       if(authentication.isAuthenticated()){
           return jwtUtil.generateToken(systemUserDetailsService.loadUserByUsername(userDTO.getUsername()));
       }else {
           throw new UsernameNotFoundException("Invalid Credentials");
       }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<StandardResponse> signInUser(@RequestBody UserDTO userDTO){
        String token = userService.signIn(userDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",token),
                HttpStatus.ACCEPTED
        );
    }

}
//
//
//    @PostMapping("/signup")
//    public ResponseEntity<StandardResponse> signUpUser(@RequestBody UserDTO userDTO){
//        String user = userService.signUp(userDTO);
//        return new ResponseEntity<StandardResponse>(
//            new StandardResponse(200,"Success",user),
//                HttpStatus.ACCEPTED
//        );
//    }
//
