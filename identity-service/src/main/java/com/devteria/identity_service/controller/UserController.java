package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.ApiResponse;
import com.devteria.identity_service.dto.request.UserCreationRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("v1/users/create")
    User createUser(@RequestBody @Valid UserCreationRequest request){
        return userService.createUser(request);
    }

    @PostMapping("v2/users/create")
    ApiResponse createUserV2(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();

        User user = userService.createUser(request);

        apiResponse.setResult(user);
        apiResponse.setMessage(String.format("Tạo mới user %s thành công", user.getUsername()));

        return apiResponse;
    }

    @GetMapping("/users/list")
    List<User> getUserList(){
        return userService.getUsers();
    }

    @GetMapping("users/detail/{userId}")
    UserResponse getUserDetail(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("users/update/{userId}")
    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("users/delete/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "Đã xóa thành công user " + userId;
    }

}
