package com.devteria.identity_service.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min=3, message="Username must be at least 3 characters")
    String username;

    @Size(min=8, max=16, message="Password must be at least 8 characters")
    String password;

    @Email(message="EMAIL_EXISTED")
    @NotBlank(message="EMAIL_BLANK")
    String email;

    String firstName;
    String lastName;

    LocalDate dob;


}
