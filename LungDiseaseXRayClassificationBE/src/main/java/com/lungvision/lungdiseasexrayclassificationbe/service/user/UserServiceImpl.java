package com.lungvision.lungdiseasexrayclassificationbe.service.user;

import com.lungvision.lungdiseasexrayclassificationbe.exception.UserNotFoundException;
import com.lungvision.lungdiseasexrayclassificationbe.model.User;
import com.lungvision.lungdiseasexrayclassificationbe.repository.UserRepository;
import com.lungvision.lungdiseasexrayclassificationbe.security.model.UserSecurityDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserDetails loadUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UserNotFoundException(email);
        }

        return new UserSecurityDetails(user.get());
    }

    @Override
    public UserDetails loadUserById(String id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        return new UserSecurityDetails(user.get());
    }
}
