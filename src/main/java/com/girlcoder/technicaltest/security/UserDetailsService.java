package com.girlcoder.technicaltest.security;

import com.girlcoder.technicaltest.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        com.girlcoder.technicaltest.security.UserDetails userDetails = null;
        if (user != null){
            userDetails = new com.girlcoder.technicaltest.security.UserDetails();
            userDetails.setUser(user);
        }else {
            throw  new UsernameNotFoundException("user with username " + username + "does not exist");
        }
        return userDetails;
    }
}
