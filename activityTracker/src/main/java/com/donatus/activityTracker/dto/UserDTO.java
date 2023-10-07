package com.donatus.activityTracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private  String password;
    private String userName;
    private String occupation;
    private String address;


    public UserDTO(String firstName, String lastName, String email, String password, String userName, String occupation, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.occupation = occupation;
        this.address = address;
    }
}
