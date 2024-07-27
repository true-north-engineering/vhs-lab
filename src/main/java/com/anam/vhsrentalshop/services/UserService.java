package com.anam.vhsrentalshop.services;

import com.anam.vhsrentalshop.domain.entities.UserEntity;
import com.anam.vhsrentalshop.domain.entities.VhsEntity;

import java.util.Optional;

public interface UserService {

    Optional<UserEntity> getUserById(Long id);
}
