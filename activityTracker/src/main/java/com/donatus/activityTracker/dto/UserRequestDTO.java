package com.donatus.activityTracker.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private  String password;
    private String userName;
    private String occupation;
    private String address;


    public UserRequestDTO(String firstName, String lastName, String email, String password, String userName, String occupation, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.occupation = occupation;
        this.address = address;
    }
}
