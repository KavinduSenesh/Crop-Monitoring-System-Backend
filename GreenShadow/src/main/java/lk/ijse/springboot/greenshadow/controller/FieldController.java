package lk.ijse.springboot.greenshadow.controller;

import jakarta.validation.Valid;
import lk.ijse.springboot.greenshadow.customObj.FieldResponse;
import lk.ijse.springboot.greenshadow.dto.impl.FieldDTO;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.FieldNotFoundException;
import lk.ijse.springboot.greenshadow.service.FieldService;
import lk.ijse.springboot.greenshadow.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/field")
public class FieldController {

    private final static Logger logger = LoggerFactory.getLogger(FieldController.class);

    private final FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveField(
        @RequestParam("field_name") String fieldName,
        @RequestParam("latitude") double latitude,
        @RequestParam("longitude") double longitude,
        @RequestParam("field_size") double fieldSize,
        @RequestParam("field_image1") MultipartFile fieldImage1,
        @RequestParam("field_image2") MultipartFile fieldImage2,
        @RequestParam("staff_ids") List<String> staffIds
    )
    {
        if (fieldName == null || fieldImage1 == null || fieldImage2 == null){
            logger.warn("Field name or Field Image is null");
            return ResponseEntity.badRequest().build();
        } else{
            FieldDTO fieldDTO = new FieldDTO();
            try{
                fieldDTO.setFieldName(fieldName);
                fieldDTO.setFieldLocation(new Point((int) latitude, (int) longitude));
                fieldDTO.setFieldSize(fieldSize);
                fieldDTO.setFieldImage1(AppUtil.toBase64Pic(fieldImage1));
                fieldDTO.setFieldImage2(AppUtil.toBase64Pic(fieldImage2));
                fieldDTO.setStaffIds(staffIds.isEmpty() ? null : staffIds);
                fieldService.saveField(fieldDTO);
                logger.info("Field with Field Code: {} saved successfully", fieldDTO.getFieldCode());
                return ResponseEntity.ok().build();
            }catch (DataPersistFailedException e){
                logger.error("Failed to save field: {}", fieldDTO, e);
                return ResponseEntity.badRequest().build();
            }catch (Exception e){
                logger.error("Internal server error while saving field: {}", fieldDTO, e);
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @DeleteMapping("/{field_code}")
    public ResponseEntity<Void> deleteField(@PathVariable ("field_code") String fieldCode) {
        try {
            fieldService.deleteField(fieldCode);
            logger.info("Field with Field Code: {} deleted successfully", fieldCode);
            return ResponseEntity.ok().build();
        }catch (FieldNotFoundException e){
            logger.error("Failed to delete field with Field Code: {}", fieldCode, e);
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            logger.error("Internal server error while deleting field with Field Code: {}", fieldCode, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(value = "/{field_code}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateField(
            @PathVariable("field_code") String fieldCode,
            @Valid @RequestParam("field_name") String fieldName,
            @Valid @RequestParam("latitude") double latitude,
            @Valid @RequestParam("longitude") double longitude,
            @Valid @RequestParam("field_size") double fieldSize,
            @RequestParam("field_image1") MultipartFile fieldImage1,
            @RequestParam("field_image2") MultipartFile fieldImage2,
            @RequestParam("staff_ids") List<String> staffIds
    ){
        if (fieldName == null || fieldImage1 == null || fieldImage2 == null){
            logger.warn("Field object or field image is null");
            return ResponseEntity.badRequest().build();
        }else {
            FieldDTO fieldDTO = new FieldDTO();
            try{
                fieldDTO.setFieldName(fieldName);
                fieldDTO.setFieldLocation(new Point((int) latitude, (int) longitude));
                fieldDTO.setFieldSize(fieldSize);
                fieldDTO.setFieldImage1(AppUtil.toBase64Pic(fieldImage1));
                fieldDTO.setFieldImage2(AppUtil.toBase64Pic(fieldImage2));
                fieldDTO.setStaffIds(staffIds.isEmpty() ? null : staffIds);
                fieldService.updateField(fieldCode, fieldDTO);
                logger.info("Field with Field Code: {} updated successfully", fieldCode);
                return ResponseEntity.noContent().build();
            } catch (FieldNotFoundException e){
                logger.error("Field with Field Code: {} not found for update", fieldCode, e);
                return ResponseEntity.notFound().build();
            }catch (Exception e){
                logger.error("Internal server error");
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @GetMapping(value = "/{field_code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldResponse getSelectedField(@PathVariable("field_code") String fieldCode){
        return fieldService.getFieldByFieldCode(fieldCode);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAlFields(){
        return fieldService.getAllFields();
    }
}
