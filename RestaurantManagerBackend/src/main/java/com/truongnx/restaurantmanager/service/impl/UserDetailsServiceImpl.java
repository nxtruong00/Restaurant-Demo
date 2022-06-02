package com.truongnx.restaurantmanager.service.impl;

import com.truongnx.restaurantmanager.dto.UserDto;
import com.truongnx.restaurantmanager.model.Role;
import com.truongnx.restaurantmanager.model.User;
import com.truongnx.restaurantmanager.repository.UserRepository;
import com.truongnx.restaurantmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with the username of: " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        User user = optionalUser.get();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

//    @Override
//    public UserDto getUserByUsername(String username) {
//        User user = userRepository.findByUsername(username);
//        user.setPassword("");
//        List<String> roles = new ArrayList<>();
//        for (Role role : user.getRoles()) {
//            roles.add(role.getRoleName());
//        }
//        return UserDto.builder().username(user.getUsername()).roles(roles).build();
//    }

    @Override
    public UserDto getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with the username of: " + username);
        }
        User user = optionalUser.get();
        user.setPassword("");
        List<String> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRoleName());
        }
        return UserDto.builder().username(user.getUsername()).roles(roles).build();
    }
}
