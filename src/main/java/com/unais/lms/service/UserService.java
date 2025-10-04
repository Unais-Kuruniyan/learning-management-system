package com.unais.lms.service;

import com.unais.lms.dto.UserRequest;
import com.unais.lms.dto.UserResponse;
import com.unais.lms.exception.ResourceNotFoundException;
import com.unais.lms.mappers.UserMapper;
import com.unais.lms.model.User;
import com.unais.lms.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::mapToUserResponse).toList();
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        return userMapper.mapToUserResponse(user);

    }

    public UserResponse createUser(UserRequest user) {
        User savedUser = userRepository.save(userMapper.mapToUser(user));
        return userMapper.mapToUserResponse(savedUser);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setRole(userRequest.role());
        userRepository.save(user);
        return userMapper.mapToUserResponse(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        userRepository.delete(user);
    }
}
