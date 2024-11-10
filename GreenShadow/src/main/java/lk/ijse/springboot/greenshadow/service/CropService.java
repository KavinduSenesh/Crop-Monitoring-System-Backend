package lk.ijse.springboot.greenshadow.service;

import jakarta.validation.Valid;
import lk.ijse.springboot.greenshadow.customObj.CropResponse;
import lk.ijse.springboot.greenshadow.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(@Valid CropDTO cropDTO);
    void deleteCrop(String cropCode);
    void updateCrop(String cropCode, CropDTO cropDTO);
    CropResponse getSelectedCrop(String cropCode);
    List<CropDTO> getAllCrops();
}
