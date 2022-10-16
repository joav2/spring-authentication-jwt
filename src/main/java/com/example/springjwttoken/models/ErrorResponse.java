package com.example.springjwttoken.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(Integer httpStatus,
                            String excepiton,
                            String message,
                            List<FieldError> fieldsErrors) {
}
