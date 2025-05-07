package com.raphmei.eventplanner.service;

import com.raphmei.eventplanner.dto.UserDTO;
import com.raphmei.eventplanner.entity.User;
import com.raphmei.eventplanner.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(User user) {
        if(userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User with id " + user.getEmail() + " already exists");
        }else {
            User savedUser = userRepository.save(user);
            return mapToUserDTO(savedUser);
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserDTO)
                .toList();
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " does not exist"));
        return mapToUserDTO(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " does not exist"));
        userRepository.deleteById(id);
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }
}
