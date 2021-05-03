package com.girlcoder.technicaltest;

import com.girlcoder.technicaltest.model.Role;
import com.girlcoder.technicaltest.respository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class TechnicaltestApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(TechnicaltestApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder();}

    @Override
    public void run(String... args) throws Exception{
       if (roleRepository.count() == 0){
           Role role = new Role();
           role.setRole("USER");
           roleRepository.save(role);
       }
    }
}
