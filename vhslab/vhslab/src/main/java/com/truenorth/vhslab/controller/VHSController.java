package com.truenorth.vhslab.controller;

import com.truenorth.vhslab.exception.CustomException;
import com.truenorth.vhslab.model.VHS;
import com.truenorth.vhslab.service.VHSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vhs")
@Slf4j
public class VHSController {

    @Autowired
    private VHSService vhsService;

    @GetMapping(value = "")
    public List<VHS> getAllVHS() {
        log.info("Get vhs list");
        return vhsService.getAllVHS();
    }

    @GetMapping(value = "/{id}")
    public VHS getByVHSId(@PathVariable Long id) {
        log.info("Get vhs by id " + id);
        return vhsService.getByVHSId(id);
    }

    @PostMapping(value = "")
    public VHS createVHS(@Valid @RequestBody VHS vhs) {
        log.info("Create VHS " + vhs.toString());
        return vhsService.createVHS(vhs);
    }

    @PutMapping(value = "/{id}")
    @ExceptionHandler({CustomException.class})
    public VHS updateVHS(@Valid @RequestBody VHS vhs, @PathVariable Long id) {
        log.info("Update VHS " + vhs.toString());
        if (vhsService.getByVHSId(id) == null) {
            throw new CustomException("VHS not found");
        }
        return vhsService.updateVHS(vhs, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteVHS(@PathVariable Long id) {
        log.info("Delete VHS " + id);
        vhsService.deleteVHS(id);
    }
}
