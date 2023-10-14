package com.donatus.activityTracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "*required")
    @Size(min = 2, max = 45, message = "Min 2, max 45")
    private String firstName;

    private String lastName;

    @NotBlank(message = "*required")
    @Size(min = 10, max = 25, message = "Min 10, max 25")
    private String email;

    @NotBlank(message = "*required")
    @Size(min = 4, max = 15, message = "Min 4, max 15")
    private  String password;

    @NotBlank(message = "*required")
    @Size(min = 3, max = 10, message = "Min 3, max 10")
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
