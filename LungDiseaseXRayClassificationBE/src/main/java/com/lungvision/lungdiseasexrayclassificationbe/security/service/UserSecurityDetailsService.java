package com.lungvision.lungdiseasexrayclassificationbe.security.service;

import com.lungvision.lungdiseasexrayclassificationbe.model.User;
import com.lungvision.lungdiseasexrayclassificationbe.repository.UserRepository;
import com.lungvision.lungdiseasexrayclassificationbe.security.model.UserSecurityDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecurityDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserSecurityDetails(user.get());
    }
}
