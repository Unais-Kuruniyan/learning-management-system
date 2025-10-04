package com.unais.lms.dto;

import com.unais.lms.model.User;

public record UserRequest(

        String email,
        String password,
        User.Role role
) {
}
