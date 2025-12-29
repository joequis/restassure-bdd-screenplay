package com.company.reqres.mapper;

import com.company.reqres.model.users.UserResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserMapper {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static UserResponseDto fromJson(String json) {
        try {
            return MAPPER.readValue(json, UserResponseDto.class);
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo mapear UserResponseDto", e);
        }
    }
}

