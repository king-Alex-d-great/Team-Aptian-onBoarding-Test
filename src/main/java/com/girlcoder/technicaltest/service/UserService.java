package com.girlcoder.technicaltest.service;

import com.girlcoder.technicaltest.model.User;
import com.girlcoder.technicaltest.model.dto.CreateUserDto;

public interface UserService {
    User createUser(CreateUserDto userDto);
    User findByUsername(String username);
}
