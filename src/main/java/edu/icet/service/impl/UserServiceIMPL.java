package edu.icet.service.impl;

import edu.icet.dto.UserDTO;
import edu.icet.entity.SystemUser;
import edu.icet.exception.NotFoundException;
import edu.icet.repository.UserRepository;
import edu.icet.service.UserService;
//import edu.icet.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {
    private final UserRepository userRepository;
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    public UserServiceIMPL(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }


//    @Override
//    public String signUp(UserDTO userDTO) {
//        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
//        try{
//            if(userRepository.existsById(userDTO.getId())) {
//                throw new EntityExistsException("User already exists");
//            }
////            UserInfo user = modelMapper.map(userDTO, UserInfo.class);
////            userRepository.save(modelMapper.map(userDTO, UserInfo.class));
////            return user.getUsername();
//            SystemUser systemUser = modelMapper.map(userDTO, SystemUser.class);
//            userRepository.save(systemUser);
////            System.out.println("..............sapumal............");
//            return systemUser.getUsername();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            throw new RuntimeException("Error while signing up");
//        }
//
//    }

    @Override
    public String signIn(UserDTO userDTO) {
        try{
            Optional<SystemUser> user = userRepository.findByUsername(userDTO.getUsername());
            if(user.isPresent()) {
                SystemUser userInfo = user.get();
                if(bCryptPasswordEncoder.matches(userDTO.getPassword(), userInfo.getPassword())) {
                    return userInfo.getUsername();
                }
                else{
                    throw new NotFoundException("Username Password not matched");
                }
            }
            else{
                throw new NotFoundException("User not found");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error while signing in");
        }
//        try{
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(),
//                    authRequestDTO.getPassword()));
//            if (authentication.isAuthenticated()) {
//                return jwtUtil.generateToken(authRequestDTO.getUsername());
//            }
////            else {
////                return "fail";
////            }
//        }catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Login failed: " + e.getMessage());
//        }

    }

//    public String verify(UserDTO userDTO) {
//
//
//        Authentication authentication=authenticationManager.
//                authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(),userDTO.getPassword()));
//
//        if(authentication.isAuthenticated()){
//            return jwtUtil.generateToken(userDTO.getUsername());
//        }
//        return "not success";
//    }

}


