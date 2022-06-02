package com.truongnx.restaurantmanager.service;

import com.truongnx.restaurantmanager.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto getUserByUsername(String username);

     UserDetails loadUserByUsername(String username);
}
