package lk.ijse.springboot.greenshadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springboot.greenshadow.customObj.CropResponse;
import lk.ijse.springboot.greenshadow.customObj.impl.CropErrorResponse;
import lk.ijse.springboot.greenshadow.dto.impl.CropDTO;
import lk.ijse.springboot.greenshadow.entity.Crop;
import lk.ijse.springboot.greenshadow.entity.Field;
import lk.ijse.springboot.greenshadow.exception.CropNotFoundException;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.FieldNotFoundException;
import lk.ijse.springboot.greenshadow.repository.CropRepository;
import lk.ijse.springboot.greenshadow.repository.FieldRepository;
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
    private final FieldRepository fieldRepository;

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
            throw new CropNotFoundException("Crop not found");
        }else {
            cropRepository.deleteById(cropCode);
        }
    }

    @Override
    public void updateCrop(CropDTO cropDTO, String fieldCode, String id) {
        Optional<Crop> byCropCode = cropRepository.findById(id);
        if (byCropCode.isPresent()){
            Field field = fieldRepository.findById(fieldCode).orElseThrow(
                    () -> new FieldNotFoundException("Field not found")
            );
            byCropCode.get().setField(field);
            byCropCode.get().setCropCommonName(cropDTO.getCropCommonName());
            byCropCode.get().setCategory(cropDTO.getCategory());
            byCropCode.get().setCropSeason(cropDTO.getCropSeason());
            byCropCode.get().setCropScientificName(cropDTO.getCropScientificName());
            byCropCode.get().setCropImage(cropDTO.getCropImage());
        }else {
            throw new CropNotFoundException("Crop not found");
        }
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
