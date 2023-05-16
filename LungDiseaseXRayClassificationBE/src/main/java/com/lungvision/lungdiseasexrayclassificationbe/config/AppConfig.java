package com.lungvision.lungdiseasexrayclassificationbe.config;

import com.lungvision.lungdiseasexrayclassificationbe.model.Role;
import com.lungvision.lungdiseasexrayclassificationbe.model.User;
import com.lungvision.lungdiseasexrayclassificationbe.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Configuration
@Slf4j
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    CommandLineRunner run(UserRepository userRepository){
//        return args ->{
//            User user = new User();
//            user.setEmail("admin@gmail.com");
//            user.setPassword(passwordEncoder().encode("admin"));
//            user.setRole(Role.ROLE_USER);
//            userRepository.save(user);
//        };
//    }

}
