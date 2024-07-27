package com.anam.vhsrentalshop.controllers;

import com.anam.vhsrentalshop.domain.entities.VhsEntity;
import com.anam.vhsrentalshop.services.VhsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vhs")
public class VhsController {

    private VhsService vhsService;

    public VhsController(VhsService vhsService) {
        this.vhsService = vhsService;
    }

    @GetMapping(path="")
    public ResponseEntity<List<VhsEntity>> getAllVhs() {
        return new ResponseEntity<>(vhsService.findAll(), HttpStatus.OK);
    }
}
