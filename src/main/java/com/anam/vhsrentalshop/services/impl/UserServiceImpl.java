package com.anam.vhsrentalshop.services.impl;

import com.anam.vhsrentalshop.domain.entities.UserEntity;
import com.anam.vhsrentalshop.repositories.UserRepository;
import com.anam.vhsrentalshop.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return repository.findById(id);
    }
}
