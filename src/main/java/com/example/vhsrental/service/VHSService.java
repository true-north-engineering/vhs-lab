package com.example.vhsrental.service;

import com.example.vhsrental.entity.VHS;

import java.util.List;

public interface VHSService {
    List<VHS> getAllVHS();
    VHS getVHSById(Long id);
    VHS createVHS(VHS vhs);
    VHS updateVHS(Long id, VHS vhsDetails);
    void deleteVHS(Long id);
}
