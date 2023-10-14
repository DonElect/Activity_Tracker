package com.donatus.activityTracker.dto.mapper;

import com.donatus.activityTracker.dto.UserRequestDTO;
import com.donatus.activityTracker.entity.Users;
import com.donatus.activityTracker.utils.PasswordEncryption;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class UserDTORequestMapperImpl implements UserDTORequestMapperService{
    private final Users user = new Users();
    private final PasswordEncryption encryption = new PasswordEncryption();

    @Override
    public Users mapper(UserRequestDTO userDTO){
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUserName());
        user.setPassword("{bcrypt}"+encryption.encryptPassword(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setOccupation(userDTO.getOccupation());
        user.setAddress(userDTO.getAddress());

        return user;
    }
}
