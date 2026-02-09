package com.sgr.outlayehservice.Services.Interface;

import com.sgr.outlayehservice.dto.UserDto;
import com.sgr.outlayehservice.response.OutlayEhServiceResponse;

import java.util.List;

public interface UserService {
    OutlayEhServiceResponse<UserDto> createUser(UserDto dto);
    OutlayEhServiceResponse<UserDto> updateUser(Long id, UserDto dto);
    OutlayEhServiceResponse<UserDto> getUserById(Long id);
    OutlayEhServiceResponse<List<UserDto>> getAllUsers();
    OutlayEhServiceResponse<Void> deleteUser(Long id);
}