package com.mynerdygarage.user.service.util;

import com.mynerdygarage.user.dto.NewUserDto;
import com.mynerdygarage.user.model.User;
import com.mynerdygarage.util.CustomFormatter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

public class UserCreator {


    public static User create(PasswordEncoder passwordEncoder, NewUserDto newUserDto) {

        User user = new User();

        user.setName(newUserDto.getName());
        user.setEmail(newUserDto.getEmail());
        user.setBirthDate(CustomFormatter.stringToDate(newUserDto.getBirthDate()));
        user.setRegDate(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(newUserDto.getPassword()));

        return user;
    }

}
