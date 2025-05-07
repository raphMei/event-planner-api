package com.raphmei.eventplanner.service;

import com.raphmei.eventplanner.dto.UserDTO;
import com.raphmei.eventplanner.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(User user);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    void deleteUserById(Long id);
}
