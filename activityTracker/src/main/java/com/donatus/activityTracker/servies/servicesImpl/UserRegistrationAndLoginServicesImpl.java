package com.donatus.activityTracker.servies.servicesImpl;

import com.donatus.activityTracker.entity.Users;
import com.donatus.activityTracker.repository.UsersRepository;
import com.donatus.activityTracker.servies.UserRegistrationAndLoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationAndLoginServicesImpl implements UserRegistrationAndLoginServices {
    private final UsersRepository usersRepository;

    @Autowired
    public UserRegistrationAndLoginServicesImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users verifyUser(String userName, String password) {
        return usersRepository.findByUserNameAndPassword(userName, password);
    }

    @Transactional
    @Override
    public Users registerUser(Users user) {
        return usersRepository.save(user);
    }
}
