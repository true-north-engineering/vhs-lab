package com.anam.vhsrentalshop.services.impl;

import com.anam.vhsrentalshop.domain.entities.VhsEntity;
import com.anam.vhsrentalshop.repositories.VhsRepository;
import com.anam.vhsrentalshop.services.VhsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VhsServiceImpl implements VhsService {

    private VhsRepository repository;

    public VhsServiceImpl(VhsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VhsEntity> findAll() {
        return StreamSupport.stream(repository
                .findAll()
                .spliterator(),
                false)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<VhsEntity> getVhsById(Long id) {
        return repository.findById(id);
    }

    @Override
    public VhsEntity save(VhsEntity vhsEntity) {
        return repository.save(vhsEntity);
    }

    @Override
    public boolean isExists(Long id) {
        return repository.existsById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
