package com.ecommerce.entity;

import com.ecommerce.enums.Role;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String userName;
    private String address;
    private String password;
    private String email;
    private String phone;
    private Role role;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
}
