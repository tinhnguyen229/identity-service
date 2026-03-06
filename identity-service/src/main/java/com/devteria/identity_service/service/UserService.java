package com.devteria.identity_service.service;


import com.devteria.identity_service.dto.request.UserCreationRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.exception.BusinessException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User newUser = new User();
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


        newUser.setUsername(username);
        newUser.setPassword(request.getPassword());
        newUser.setEmail(email);
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setBirthday(request.getDob());

        return userRepository.save(newUser);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = this.getUser(userId);

        String email = request.getEmail();
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        return userRepository.save(user);

    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

}
