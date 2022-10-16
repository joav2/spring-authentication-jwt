package com.example.springjwttoken.security;

import com.example.springjwttoken.web.dto.UserDTO;
import com.example.springjwttoken.services.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userService.getByUsername(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException(username);
        }

        List<SimpleGrantedAuthority> authorities = userDTO.roles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return mapUserDTOToCustomUserDetails(userDTO, authorities);
    }

    private CustomUserDetails mapUserDTOToCustomUserDetails(UserDTO userDTO, List<SimpleGrantedAuthority> authorities) {
        CustomUserDetails customUserDetails = new CustomUserDetails(userDTO);
        customUserDetails.setAuthorities(authorities);

        return customUserDetails;
    }
}
