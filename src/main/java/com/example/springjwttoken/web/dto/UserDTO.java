package com.example.springjwttoken.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.example.springjwttoken.entities.User} entity
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDTO(Long id,
                      String username,
                      String password,
                      String firstName,
                      String lastName,
                      String email,
                      List<String> roles
) implements Serializable {

}
