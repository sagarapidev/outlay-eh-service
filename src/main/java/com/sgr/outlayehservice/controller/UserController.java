package com.sgr.outlayehservice.controller;

import com.sgr.outlayehservice.Services.Interface.UserService;
import com.sgr.outlayehservice.dto.UserDto;
import com.sgr.outlayehservice.response.OutlayEhServiceResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public OutlayEhServiceResponse<UserDto> createUser(@RequestBody UserDto dto) {
        return userService.createUser(dto);
    }
}