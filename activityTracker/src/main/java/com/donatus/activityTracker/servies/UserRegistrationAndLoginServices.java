package com.donatus.activityTracker.servies;

import com.donatus.activityTracker.entity.Users;
import com.donatus.activityTracker.exception.InvalidUserPassword;

public interface UserRegistrationAndLoginServices {
    Users verifyUser(String userEmail, String password) throws InvalidUserPassword;
    Users registerUser(Users user);

}
