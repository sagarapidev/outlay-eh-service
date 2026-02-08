package com.sgr.outlayehservice.Services.Impl;

import com.sgr.outlayehservice.Services.Interface.UserService;
import com.sgr.outlayehservice.dto.UserDto;
import com.sgr.outlayehservice.model.User;
import com.sgr.outlayehservice.repository.UserRepository;
import com.sgr.outlayehservice.response.OutlayEhServiceResponse;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OutlayEhServiceResponse<UserDto> createUser(UserDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            return OutlayEhServiceResponse.<UserDto>builder()
                    .status("ERROR")
                    .message("Email already exists")
                    .build();
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .createdOn(LocalDateTime.now())
                .build();

        User saved = userRepository.save(user);
        return OutlayEhServiceResponse.<UserDto>builder()
                .status("SUCCESS")
                .message("User created successfully")
                .data(toDto(saved))
                .build();
    }

    @Override
    public OutlayEhServiceResponse<UserDto> updateUser(Long id, UserDto dto) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(dto.getName());
                    user.setEmail(dto.getEmail());
                    user.setUpdatedOn(LocalDateTime.now());
                    User updated = userRepository.save(user);
                    return OutlayEhServiceResponse.<UserDto>builder()
                            .status("SUCCESS")
                            .message("User updated successfully")
                            .data(toDto(updated))
                            .build();
                })
                .orElseGet(() -> OutlayEhServiceResponse.<UserDto>builder()
                        .status("ERROR")
                        .message("User not found")
                        .build());
    }

    @Override
    public OutlayEhServiceResponse<UserDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> OutlayEhServiceResponse.<UserDto>builder()
                        .status("SUCCESS")
                        .message("User retrieved successfully")
                        .data(toDto(user))
                        .build())
                .orElseGet(() -> OutlayEhServiceResponse.<UserDto>builder()
                        .status("ERROR")
                        .message("User not found")
                        .build());
    }

    @Override
    public OutlayEhServiceResponse<List<UserDto>> getAllUsers() {
        List<UserDto> users = userRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return OutlayEhServiceResponse.<List<UserDto>>builder()
                .status("SUCCESS")
                .message("Users retrieved successfully")
                .data(users)
                .build();
    }

    @Override
    public OutlayEhServiceResponse<Void> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return OutlayEhServiceResponse.<Void>builder()
                    .status("ERROR")
                    .message("User not found")
                    .build();
        }
        userRepository.deleteById(id);
        return OutlayEhServiceResponse.<Void>builder()
                .status("SUCCESS")
                .message("User deleted successfully")
                .build();
    }

    private UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdOn(user.getCreatedOn().toString())
                .updatedOn(user.getUpdatedOn() != null ? user.getUpdatedOn().toString() : null)
                .build();
    }
}