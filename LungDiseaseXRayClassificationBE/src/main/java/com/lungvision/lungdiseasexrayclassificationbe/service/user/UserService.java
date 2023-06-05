package com.lungvision.lungdiseasexrayclassificationbe.service.user;

import com.lungvision.lungdiseasexrayclassificationbe.dto.NewUserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserById(String id);

    String register(NewUserDto newUserDto);
}
