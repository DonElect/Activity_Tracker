package com.donatus.activityTracker.servies;

import com.donatus.activityTracker.entity.Users;

public interface UserRegistrationAndLoginServices {
    Users verifyUser(String userName, String password);
    Users registerUser(Users user);

}
