package lk.ijse.springboot.greenshadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springboot.greenshadow.customObj.CropResponse;
import lk.ijse.springboot.greenshadow.customObj.impl.CropErrorResponse;
import lk.ijse.springboot.greenshadow.dto.impl.CropDTO;
import lk.ijse.springboot.greenshadow.entity.Crop;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.repository.CropRepository;
import lk.ijse.springboot.greenshadow.service.CropService;
import lk.ijse.springboot.greenshadow.util.AppUtil;
import lk.ijse.springboot.greenshadow.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService{
    private final CropRepository cropRepository;
    private final Mapping mapping;


    @Override
    public void saveCrop(CropDTO cropDTO) {
        cropDTO.setCropCode(AppUtil.generateCropCode());
        Crop save = cropRepository.save(mapping.convertCropDTOToCrop(cropDTO));
        if (save.getCropCode() == null){
            throw new DataPersistFailedException("Failed to save crop");
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<Crop> crop = cropRepository.findById(cropCode);
        if (crop.isEmpty()){
            throw new DataPersistFailedException("Failed to delete crop");
        }else {
            cropRepository.deleteById(cropCode);
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {
        cropRepository.findById(cropCode).ifPresentOrElse(
                selectedCrop -> {
                    cropDTO.setCropCode(selectedCrop.getCropCode());
                    cropRepository.save(mapping.convertCropDTOToCrop(cropDTO));
                }, () -> {
                    throw new DataPersistFailedException("Failed to update crop");
                }
        );
    }

    @Override
    public CropResponse getSelectedCrop(String cropCode) {
        if (cropRepository.existsById(cropCode)){
            return mapping.convertCropToCropDTO(cropRepository.getById(cropCode));
        } else {
            return new CropErrorResponse(404, "Crop not found");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return mapping.convertCropListToCropDTOList(cropRepository.findAll());
    }
}
