package com.example.springjwttoken.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LoginRequest(@NotBlank String username,
                           @NotBlank String password) {
}
