package com.example.springjwttoken.services.impl;

import com.example.springjwttoken.entities.Role;
import com.example.springjwttoken.entities.User;
import com.example.springjwttoken.web.dto.UserDTO;
import com.example.springjwttoken.repositories.UserRepository;
import com.example.springjwttoken.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public boolean hasUserWithUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = false)
    public UserDTO create(UserDTO userDTO) {
        Role role1 = new Role(1L, "ROLE_NORMAL");

        User user = new User();
        mapToEntity(userDTO, user);
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword("{bcrypt}"+encodedPassword);
        user.setRoles(List.of(role1));

        return mapToDTO(userRepository.save(user));
    }

    private UserDTO mapToDTO(User user) {
        List<String> roleNames = user.getRoles()
                .stream()
                .map(Role::getRole)
                .toList();
        return new UserDTO(
                user.getId(), user.getUsername(),
                user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getEmail(),
                roleNames
        );
    }

    private User mapToEntity(UserDTO userDTO, User user) {
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setFirstName(userDTO.firstName());
        user.setEmail(userDTO.email());

        return user;
    }
}
