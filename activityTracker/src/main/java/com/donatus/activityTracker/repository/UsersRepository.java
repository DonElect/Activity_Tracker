package com.donatus.activityTracker.repository;

import com.donatus.activityTracker.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    //Users findByUserNameAndPassword(String userName, String password);
    Optional<Users> findUsersByEmail(String email);
}
