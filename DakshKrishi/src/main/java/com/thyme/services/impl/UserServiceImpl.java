package com.thyme.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thyme.entities.User;
import com.thyme.exceptions.ResourceNotFoundException;
import com.thyme.payloads.UserDto;
import com.thyme.repositories.UserRepo;
import com.thyme.services.UserService;


@Service
public class UserServiceImpl implements UserService, UserDetailsService  {
    
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setImageURL(userDto.getImageURL());
        user.setAbout(userDto.getAbout());
        User updatedUser = this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return this.userToDto(user);
    }

    @Override
    public UserDto authenticateUser(String userName, String password) {
        User user = this.userRepo.getUserByName(userName);
        if (user == null) {
            throw new ResourceNotFoundException("User", "name", userName);
        }

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid credentials, please check your username and password.");
        }

        return this.userToDto(user);  // Return UserDto after authentication
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepo.delete(user);
    }

    private User dtoUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setImageURL(userDto.getImageURL());
        return user;
    }

    private UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());  // Corrected mapping
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setImageURL(user.getImageURL());
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Not implemented for this case
        return null;
    }
}

