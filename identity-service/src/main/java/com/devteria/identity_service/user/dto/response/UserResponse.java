package com.devteria.identity_service.user.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
//    String password;
    String email;
    String firstName;
    String lastName;
    LocalDate dob;

    String address;
    String address123;

    String other;



    public String getOther() {
        return "Xin chào " + username;
    }
}
