package com.example.vhsrental.service;

import com.example.vhsrental.entity.VHS;
import com.example.vhsrental.exception.ResourceNotFoundException;
import com.example.vhsrental.exception.VHSAssociatedWithRentalException;
import com.example.vhsrental.repository.RentalRepository;
import com.example.vhsrental.repository.VHSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VHSServiceImpl implements VHSService {

    private static final Logger logger = LoggerFactory.getLogger(VHSServiceImpl.class);

    @Autowired
    private VHSRepository vhsRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public List<VHS> getAllVHS() {
        logger.info("Fetching all VHS tapes");
        return vhsRepository.findAll();
    }

    @Override
    public VHS getVHSById(Long id) {
        logger.info("Fetching VHS with ID: {}", id);
        return vhsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VHS not found with ID: " + id));
    }

    @Override
    public VHS createVHS(VHS vhs) {
        logger.info("Creating VHS with title: {}", vhs.getTitle());
        return vhsRepository.save(vhs);
    }

    @Override
    public VHS updateVHS(Long id, VHS vhsDetails) {
        logger.info("Updating VHS with ID: {}", id);
        VHS vhs = getVHSById(id);
        vhs.setTitle(vhsDetails.getTitle());
        vhs.setGenre(vhsDetails.getGenre());
        vhs.setReleaseYear(vhsDetails.getReleaseYear());
        return vhsRepository.save(vhs);
    }

    @Override
    @Transactional
    public void deleteVHS(Long id) {
        logger.info("Deleting VHS with ID: {}", id);
        VHS vhs = getVHSById(id);

        if (!rentalRepository.findByVhs(vhs).isEmpty()) {
            throw new VHSAssociatedWithRentalException("Cannot delete VHS with associated rentals");
        }

        vhsRepository.delete(vhs);
    }
}
