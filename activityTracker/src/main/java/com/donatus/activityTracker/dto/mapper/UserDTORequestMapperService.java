package com.donatus.activityTracker.dto.mapper;

import com.donatus.activityTracker.dto.UserRequestDTO;
import com.donatus.activityTracker.entity.Users;

public interface UserDTORequestMapperService {

    Users mapper(UserRequestDTO userRequestDTO);
}
