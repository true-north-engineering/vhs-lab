package com.example.vhsrental.service;

import com.example.vhsrental.entity.User;
import com.example.vhsrental.exception.ResourceNotFoundException;
import com.example.vhsrental.repository.RentalRepository;
import com.example.vhsrental.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        logger.info("Fetching user with ID: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    @Override
    public User createUser(User user) {
        logger.info("Creating user with email: {}", user.getEmail());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        logger.info("Updating user with ID: {}", id);
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        logger.info("Deleting user with ID: {}", id);
        User user = getUserById(id);
        rentalRepository.deleteAllByUser(user);
        userRepository.delete(user);
    }
}
