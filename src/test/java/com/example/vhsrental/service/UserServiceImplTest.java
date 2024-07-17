package com.example.vhsrental.service;

import com.example.vhsrental.entity.User;
import com.example.vhsrental.exception.ResourceNotFoundException;
import com.example.vhsrental.repository.RentalRepository;
import com.example.vhsrental.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(new User()));

        userService.getAllUsers();

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById() {
        User user = new User(1L, "John Doe", "john.doe@example.com");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        userService.getUserById(1L);

        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    void createUser() {
        User user = new User(1L, "John Doe", "john.doe@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.createUser(user);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUser() {
        User user = new User(1L, "John Doe", "john.doe@example.com");
        User updatedUser = new User(1L, "John Doe", "john.doe@example.com");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        userService.updateUser(1L, user);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void deleteUser() {
        User user = new User(1L, "John Doe", "john.doe@example.com");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        doNothing().when(rentalRepository).deleteAllByUser(any(User.class));
        doNothing().when(userRepository).delete(any(User.class));

        userService.deleteUser(1L);

        verify(rentalRepository, times(1)).deleteAllByUser(any(User.class));
        verify(userRepository, times(1)).delete(any(User.class));
    }

    @Test
    void getUserByIdThrowsResourceNotFoundException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1L));
    }
}
