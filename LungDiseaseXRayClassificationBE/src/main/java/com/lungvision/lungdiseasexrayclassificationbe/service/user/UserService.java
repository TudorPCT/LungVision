package com.lungvision.lungdiseasexrayclassificationbe.service.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserByEmail(String email);

    UserDetails loadUserById(String id);
}
