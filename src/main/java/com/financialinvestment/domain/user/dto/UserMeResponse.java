package com.financialinvestment.domain.user.dto;

import com.financialinvestment.domain.auth.entity.User;

import java.time.LocalDate;

public record UserMeResponse(
        String name,
        String email,
        String provider,
        String role,
        LocalDate joinedAt
) {
    public static UserMeResponse from(User user) {
        return new UserMeResponse(
                user.getName(),
                user.getEmail(),
                user.getProvider().getCode(),
                user.getRole().name(),
                user.getCreatedDate().toLocalDate()
        );
    }
}
