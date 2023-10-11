package com.donatus.activityTracker.dto.mapper;

import com.donatus.activityTracker.dto.UserRequestDTO;
import com.donatus.activityTracker.entity.Users;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class UserDTORequestMapperImpl implements UserDTORequestMapperService{
    private final Users user = new Users();

    @Override
    public Users mapper(UserRequestDTO userDTO){
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setOccupation(userDTO.getOccupation());
        user.setAddress(userDTO.getAddress());

        return user;
    }
}
