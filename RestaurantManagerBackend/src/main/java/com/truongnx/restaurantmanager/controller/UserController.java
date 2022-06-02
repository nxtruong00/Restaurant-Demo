package com.truongnx.restaurantmanager.controller;


import com.truongnx.restaurantmanager.dto.UserDto;
import com.truongnx.restaurantmanager.security.JwtTokenProvider;
import com.truongnx.restaurantmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping(value = "/auth")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> getUserInfo() {
        UserDto userDto = userService
                .getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(userDto);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @PostMapping(value = "/logout")
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }


}
