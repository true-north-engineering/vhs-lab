package com.example.vhsrental.controller;

import com.example.vhsrental.entity.VHS;
import com.example.vhsrental.service.VHSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vhs")
public class VHSController {

    private static final Logger logger = LoggerFactory.getLogger(VHSController.class);

    @Autowired
    private VHSService vhsService;

    @GetMapping
    public List<VHS> getAllVHS() {
        logger.info("Received request to fetch all VHS tapes");
        return vhsService.getAllVHS();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VHS> getVHSById(@PathVariable Long id) {
        logger.info("Received request to fetch VHS with ID: {}", id);
        VHS vhs = vhsService.getVHSById(id);
        return ResponseEntity.ok().body(vhs);
    }

    @PostMapping
    public ResponseEntity<VHS> createVHS(@RequestBody VHS vhs) {
        logger.info("Received request to create VHS");
        VHS savedVHS = vhsService.createVHS(vhs);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVHS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VHS> updateVHS(@PathVariable Long id, @RequestBody VHS vhsDetails) {
        logger.info("Received request to update VHS with ID: {}", id);
        VHS updatedVHS = vhsService.updateVHS(id, vhsDetails);
        return ResponseEntity.ok(updatedVHS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVHS(@PathVariable Long id) {
        logger.info("Received request to delete VHS with ID: {}", id);
        vhsService.deleteVHS(id);
        return ResponseEntity.noContent().build();
    }
}
