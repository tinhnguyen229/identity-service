package com.devteria.identity_service.user.service;


import com.devteria.identity_service.user.dto.request.UserCreationRequest;
import com.devteria.identity_service.user.dto.request.UserUpdateRequest;
import com.devteria.identity_service.user.dto.response.UserResponse;
import com.devteria.identity_service.user.entity.User;
import com.devteria.identity_service.exception.BusinessException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.user.mapper.UserMapper;
import com.devteria.identity_service.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User createUser(UserCreationRequest request) {

        String username = request.getUsername();
        String email = request.getEmail();

        if (userRepository.existsByUsername(username)) {
//            throw new RuntimeException("Username existed.");
            throw new BusinessException(ErrorCode.USER_EXISTED);
        }

        if (userRepository.existsByEmail(email)) {
//            throw new RuntimeException("Email existed.");
            throw new BusinessException(ErrorCode.EMAIL_EXISTED);
        }

//        User newUser = new User();
//        newUser.setUsername(username);
//        newUser.setPassword(request.getPassword());
//        newUser.setEmail(email);
//        newUser.setFirstName(request.getFirstName());
//        newUser.setLastName(request.getLastName());
//        newUser.setBirthday(request.getDob());

        User newUser = userMapper.toUser(request);

        // Mã hóa password trước khi lưu xuống DB
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepository.save(newUser);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String userId) {
        User user = getUserById(userId);
        return userMapper.toResponse(user);
//        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    private User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = this.getUserById(userId);

//        String email = request.getEmail();
//        if (email != null && !email.isEmpty()) {
//            user.setEmail(email);
//        }
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
        userMapper.updateUser(user, request);

        return userRepository.save(user);

    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

}
