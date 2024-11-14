package lk.ijse.springboot.greenshadow.controller;

import jakarta.validation.Valid;
import lk.ijse.springboot.greenshadow.customObj.CropResponse;
import lk.ijse.springboot.greenshadow.dto.impl.CropDTO;
import lk.ijse.springboot.greenshadow.exception.CropNotFoundException;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.service.CropService;
import lk.ijse.springboot.greenshadow.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crop")
@RequiredArgsConstructor
public class CropController {
    private final CropService cropService;
    private static final Logger logger = LoggerFactory.getLogger(CropController.class);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCrop(
            @Valid @RequestParam("crop_common_name") String cropCommonName,
            @Valid @RequestParam("crop_scientific_name") String cropScientificName,
            @RequestParam("crop_image") MultipartFile cropImage,
            @Valid @RequestParam("category") String category,
            @Valid @RequestParam("crop_season") String cropSeason,
            @Valid @RequestParam("field_code") String fieldCode,
            @RequestParam("monitoring_log_codes") List<String> monitoringLogCodes
    ){
        if (cropCommonName == null || cropScientificName == null || cropImage == null || category == null || cropSeason == null || fieldCode == null) {
            logger.warn("Invalid request: Crop object or Crop Image is null");
            return ResponseEntity.badRequest().build();
        } else {
            CropDTO cropDTO = new CropDTO();
            try {
                cropDTO.setCropCommonName(cropCommonName);
                cropDTO.setCropScientificName(cropScientificName);
                cropDTO.setCropImage(AppUtil.toBase64Pic(cropImage));
                cropDTO.setCategory(category);
                cropDTO.setCropSeason(cropSeason);
                cropDTO.setFieldCode(fieldCode);
                cropDTO.setMonitoringLogCodes(monitoringLogCodes);
                cropService.saveCrop(cropDTO);
                logger.info("Crop with Crop name: {} saved successfully", cropDTO.getCropCommonName());
                return ResponseEntity.ok().build();
            }catch (DataPersistFailedException e){
                logger.error("Failed to save crop: {}", cropDTO, e);
                return ResponseEntity.badRequest().build();
            }catch (Exception e){
                logger.error("Internal server error while saving crop: {}", cropDTO, e);
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @DeleteMapping("/{crop_code}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("crop_code") String cropCode){
        try {
            if (cropCode == null || cropCode.isEmpty()){
                logger.warn("Invalid request: Crop code is null or empty");
                return ResponseEntity.badRequest().build();
            }else {
                cropService.deleteCrop(cropCode);
                logger.info("Crop with Crop code: {} deleted successfully", cropCode);
                return ResponseEntity.noContent().build();
            }
        }catch (CropNotFoundException e){
            logger.error("crop with Crop code: {} not found", cropCode, e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Internal server error while deleting crop with Crop code: {}", cropCode, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(value = "/{crop_code}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCrop(
            @PathVariable("crop_code") String cropCode,
            @Valid @RequestParam("crop_common_name") String cropCommonName,
            @Valid @RequestParam("crop_scientific_name") String cropScientificName,
            @RequestParam("crop_image") MultipartFile cropImage,
            @Valid @RequestParam("category") String category,
            @Valid @RequestParam("crop_season") String cropSeason,
            @Valid @RequestParam("field_code") String fieldCode,
            @RequestParam("monitoring_log_codes") List<String> monitoringLogCodes
    ){
        if(cropCommonName == null || cropScientificName == null || cropImage == null || category == null || cropSeason == null || fieldCode == null){
            logger.warn("Invalid request: Crop object or Crop Image is null");
            return ResponseEntity.badRequest().build();
        }else {
            CropDTO cropDTO = new CropDTO();
            try {
                cropDTO.setCropCommonName(cropCommonName);
                cropDTO.setCropScientificName(cropScientificName);
                cropDTO.setCategory(category);
                cropDTO.setCropImage(AppUtil.toBase64Pic(cropImage));
                cropDTO.setCropSeason(cropSeason);
                cropDTO.setFieldCode(fieldCode);
                cropDTO.setMonitoringLogCodes(monitoringLogCodes);
                cropService.saveCrop(cropDTO);
                logger.info("Crop with Crop code: {} updated successfully", cropDTO.getCropCode());
                return ResponseEntity.ok().build();
            }catch (CropNotFoundException e){
                logger.error("Crop with Crop code: {} not found", cropCode, e);
                return ResponseEntity.notFound().build();
            }catch (Exception e){
                logger.error("Internal server error while updating crop with crop code: {}", cropCode, e);
                return ResponseEntity.internalServerError().build();
            }
        }

    }
    @GetMapping(value = "/{crop_code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CropResponse getSelectedCrop(@PathVariable("crop_code") String cropCode){
        logger.info("Fetching crop with Crop code: {}", cropCode);
        return cropService.getSelectedCrop(cropCode);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops(){
        logger.info("Fetching all crops");
        return cropService.getAllCrops();
    }
}
