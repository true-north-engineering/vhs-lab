package com.truenorth.vhslab.service;

import com.truenorth.vhslab.model.VHS;
import com.truenorth.vhslab.repository.VHSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VHSService {

    @Autowired
    public VHSRepository vhsRepo = null;

    public List<VHS> getAllVHS() {
        List<VHS> vhsList = new ArrayList<>();
        vhsRepo.findAll().forEach(vhsList::add);
        return vhsList;
    }

    public VHS getByVHSId(Long id) {
        return vhsRepo.findByVhsId(id);
    }

    public VHS createVHS(VHS vhs) {
        return vhsRepo.save(vhs);
    }

    public VHS updateVHS(VHS vhs, Long id) {
        return vhsRepo.save(vhs);
    }

    public void deleteVHS(Long id) {
        vhsRepo.deleteById(id);
    }
}
