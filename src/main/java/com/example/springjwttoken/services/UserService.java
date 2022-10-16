package com.example.springjwttoken.services;

import com.example.springjwttoken.web.dto.UserDTO;

public interface UserService {

    UserDTO getByUsername(String username);

    boolean hasUserWithUsername(String username);

    boolean hasUserWithEmail(String email);

    UserDTO create(UserDTO userDTO);
}
