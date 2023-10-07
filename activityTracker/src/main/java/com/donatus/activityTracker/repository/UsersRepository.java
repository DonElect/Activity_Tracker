package com.donatus.activityTracker.repository;

import com.donatus.activityTracker.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUserNameAndPassword(String userName, String password);
}
