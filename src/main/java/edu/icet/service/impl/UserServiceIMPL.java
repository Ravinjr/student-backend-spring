package edu.icet.service.impl;

import edu.icet.dto.UserDTO;
import edu.icet.entity.SystemUser;
import edu.icet.entity.SystemUserDetailsService;
import edu.icet.exception.NotFoundException;
import edu.icet.repository.UserRepository;
import edu.icet.service.UserService;
import edu.icet.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final SystemUserDetailsService systemUserDetailsService;
    private final PasswordEncoder passwordEncoder;


    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    public UserServiceIMPL(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil, ModelMapper modelMapper, SystemUserDetailsService systemUserDetailsService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.systemUserDetailsService = systemUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String signIn(UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDTO.getUsername(),userDTO.getPassword()
        )) ;
        if(authentication.isAuthenticated()){
            return jwtUtil.generateToken(systemUserDetailsService.loadUserByUsername(userDTO.getUsername()));
        }else {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
//        try{
//            Optional<SystemUser> user = userRepository.findByUsername(userDTO.getUsername());
//            if(user.isPresent()) {
//                SystemUser userInfo = user.get();
//                if(bCryptPasswordEncoder.matches(userDTO.getPassword(), userInfo.getPassword())) {
//                    return userInfo.getUsername();
//                }
//                else{
//                    throw new NotFoundException("Username Password not matched");
//                }
//            }
//            else{
//                throw new NotFoundException("User not found");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new RuntimeException("Error while signing in");
//        }

    }

    @Override
    public SystemUser save(UserDTO userDTO) {
        try{
            Optional<SystemUser> user = userRepository.findByUsername(userDTO.getUsername());
            if(user.isPresent()) {
                throw new NotFoundException("Username already exists");
            }
            else {
                userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                SystemUser systemUser = modelMapper.map(userDTO, SystemUser.class);
                userRepository.save(systemUser);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error while Signup user");
        }
       return modelMapper.map(userDTO, SystemUser.class);
    }
//    systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
//    SystemUser user = userRepository.save(systemUser);

}


