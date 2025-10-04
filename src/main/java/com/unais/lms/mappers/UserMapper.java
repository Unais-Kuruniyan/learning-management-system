package com.unais.lms.mappers;

import com.unais.lms.dto.UserRequest;
import com.unais.lms.dto.UserResponse;
import com.unais.lms.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole());
    }

    public User mapToUser(UserRequest userRequest) {
        new User();
        return User.builder()
                .email(userRequest.email())
                .password(userRequest.password())
                .role(userRequest.role())
                .build();
    }
}
