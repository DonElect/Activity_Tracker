package com.donatus.activityTracker.servies.servicesImpl;

import com.donatus.activityTracker.entity.Users;
import com.donatus.activityTracker.exception.EmailNotFoundException;
import com.donatus.activityTracker.exception.InvalidUserPassword;
import com.donatus.activityTracker.repository.UsersRepository;
import com.donatus.activityTracker.servies.UserRegistrationAndLoginServices;
import com.donatus.activityTracker.utils.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserRegistrationAndLoginServicesImpl implements UserRegistrationAndLoginServices {
    private final UsersRepository usersRepository;
    private final PasswordEncryption encryption;

    @Autowired
    public UserRegistrationAndLoginServicesImpl(UsersRepository usersRepository, PasswordEncryption encryption) {
        this.usersRepository = usersRepository;
        this.encryption = encryption;
    }

    @Override
    public Users verifyUser(String userEmail, String password) throws InvalidUserPassword {
        Optional<Users> result = usersRepository.findUsersByEmail(userEmail);

        if (result.isEmpty()){
            throw new EmailNotFoundException("User with email: "+userEmail+", does not exist!");
        }

        String hashedPassword = result.get().getPassword();

        if (!encryption.isPassword(password, hashedPassword)){
            throw new InvalidUserPassword("Invalid password!");
        }

        return result.get();
    }

    @Transactional
    @Override
    public Users registerUser(Users user) {
        return usersRepository.save(user);
    }
}
