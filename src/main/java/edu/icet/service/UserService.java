package edu.icet.service;

import edu.icet.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
//    String signUp(UserDTO userDTO);

    String signIn(UserDTO userDTO);
}
