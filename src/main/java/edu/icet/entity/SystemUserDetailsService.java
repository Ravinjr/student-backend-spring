package edu.icet.entity;

import edu.icet.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SystemUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public SystemUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
        Optional<SystemUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var systemUser = user.get();
            return User.builder()
                    .password(systemUser.getPassword())
                    .username(systemUser.getUsername())
                    .authorities(getAuthorities(systemUser))
                    .build();
        }else{
            throw new UsernameNotFoundException(username);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


//    private String[] getAuthorities(SystemUser systemUser) {
//        if(systemUser.getAuthorities() == null){
//            return new String[]{"USER"};
//        }
//        return systemUser.getAuthorities().split(",");
//    }

    private List<GrantedAuthority> getAuthorities(SystemUser systemUser) {
        String[] roles = systemUser.getAuthorities() == null
                ? new String[]{"USER"}
                : systemUser.getAuthorities().split(",");
        return Arrays.stream(roles)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                .collect(Collectors.toList());
    }
}
