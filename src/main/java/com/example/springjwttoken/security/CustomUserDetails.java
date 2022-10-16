package com.example.springjwttoken.security;

import com.example.springjwttoken.web.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final UserDTO userDTO;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getFirstName() {
        return userDTO.firstName();
    }

    public String getEmail() {
        return userDTO.email();
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userDTO.password();
    }

    @Override
    public String getUsername() {
        return userDTO.username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
