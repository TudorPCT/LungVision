package com.lungvision.lungdiseasexrayclassificationbe.service.user;

import com.lungvision.lungdiseasexrayclassificationbe.dto.NewUserDto;
import com.lungvision.lungdiseasexrayclassificationbe.exception.EmailAlreadyExistsException;
import com.lungvision.lungdiseasexrayclassificationbe.exception.UserNotFoundException;
import com.lungvision.lungdiseasexrayclassificationbe.model.Role;
import com.lungvision.lungdiseasexrayclassificationbe.model.User;
import com.lungvision.lungdiseasexrayclassificationbe.repository.UserRepository;
import com.lungvision.lungdiseasexrayclassificationbe.security.model.UserSecurityDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final MessageSource messageSource;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserById(String id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        return new UserSecurityDetails(user.get());
    }

    @Override
    public String register(NewUserDto newUserDto){
        Locale locale = LocaleContextHolder.getLocale();
        Optional<User> user = userRepository.findByEmail(newUserDto.getEmail());

        if(user.isPresent())
            throw new EmailAlreadyExistsException(messageSource.getMessage("user.error.email.already.exists", null, locale));

        User newUser = new User();

        newUser.setEmail(newUserDto.getEmail());

        newUser.setPassword(passwordEncoder.encode(newUserDto.getPassword()));

        newUser.setRole(Role.ROLE_USER);

        userRepository.save(newUser);

        return messageSource.getMessage("user.success.register", null, locale);
    }
}
