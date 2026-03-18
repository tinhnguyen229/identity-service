package com.devteria.identity_service.user.mapper;

import com.devteria.identity_service.user.dto.request.UserCreationRequest;
import com.devteria.identity_service.user.dto.request.UserUpdateRequest;
import com.devteria.identity_service.user.dto.response.UserResponse;
import com.devteria.identity_service.user.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    @Mapping(source = "firstName", target = "address")
    @Mapping(source = "firstName", target = "address123")
    UserResponse toResponse(User user);


}
