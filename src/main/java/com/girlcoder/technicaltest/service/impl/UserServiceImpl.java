package com.girlcoder.technicaltest.service.impl;

import com.girlcoder.technicaltest.model.User;
import com.girlcoder.technicaltest.model.dto.CreateUserDto;
import com.girlcoder.technicaltest.respository.RoleRepository;
import com.girlcoder.technicaltest.respository.UserRepository;
import com.girlcoder.technicaltest.service.AccountService;
import com.girlcoder.technicaltest.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User createUser(CreateUserDto userDto) {

        if (userRepository.existsByUsername(userDto.getUsername())){
            return null;
        }
        var role = roleRepository.findById(1).isPresent() ? roleRepository.findById(1).get(): null;
        var user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(role);
        userRepository.save(user);
        var accountNumber = generateAccountNumber();

        var account = accountService.createAccount(accountNumber,user);
        user.setUserAccount(account);
        return userRepository.save(user);

    }

    @SneakyThrows
    @Override
    public User findByUsername(String username) {
        var user = userRepository.findByUsername(username);
        if (user == null){
            throw new Exception("User not found");
        }
        return user;
    }

    private String generateAccountNumber(){
        var rand = new Random();
        return Integer.toString(rand.nextInt(999999999));
    }
}
