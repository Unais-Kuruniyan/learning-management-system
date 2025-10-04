package com.unais.lms.dto;

import com.unais.lms.model.User;

public record UserResponse(
        Long userId,
        String email,
        User.Role role
) {
}
