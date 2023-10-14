package com.donatus.activityTracker.utils;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class PasswordEncryption {
    private static int workload = 12;


    public String encryptPassword(String textPassword){
        return BCrypt.hashpw(textPassword, BCrypt.gensalt(workload));
    }

    public boolean isPassword(String inputPassword, String hashedPassword){
        return BCrypt.checkpw(inputPassword, hashedPassword);
    }
}
