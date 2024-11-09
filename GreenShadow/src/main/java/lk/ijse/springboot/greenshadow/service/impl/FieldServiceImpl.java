package lk.ijse.springboot.greenshadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springboot.greenshadow.customObj.FieldResponse;
import lk.ijse.springboot.greenshadow.customObj.impl.FieldErrorResponse;
import lk.ijse.springboot.greenshadow.dto.impl.FieldDTO;
import lk.ijse.springboot.greenshadow.entity.Field;
import lk.ijse.springboot.greenshadow.entity.Staff;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.FieldNotFoundException;
import lk.ijse.springboot.greenshadow.repository.FieldRepository;
import lk.ijse.springboot.greenshadow.repository.StaffRepository;
import lk.ijse.springboot.greenshadow.service.FieldService;
import lk.ijse.springboot.greenshadow.util.AppUtil;
import lk.ijse.springboot.greenshadow.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;

    private final Mapping mapping;

    private final StaffRepository staffRepository;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        fieldDTO.setFieldCode(AppUtil.generateFieldId());
        Field field = fieldRepository.save(mapping.convertFieldDTOToField(fieldDTO));
        if (fieldDTO.getStaffIds() != null && !fieldDTO.getStaffIds().isEmpty()){
            List<Staff> staffList = staffRepository.findAllById(fieldDTO.getStaffIds());
            field.setStaff(staffList);
        }
        Field saved = fieldRepository.save(field);
        if (saved.getFieldCode() == null){
            throw new DataPersistFailedException("Failed to save field");
        }
    }

    @Override
    public FieldResponse getFieldByFieldCode(String fieldCode) {
        Optional<Field> field = fieldRepository.findById(fieldCode);
        if (field.isPresent()){
            return mapping.convertFieldToFieldDTO(field.get());
        }else {
            return new FieldErrorResponse(404, "Field not found");
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        fieldRepository.findById(fieldCode).ifPresentOrElse(
                field -> fieldRepository.deleteById(fieldCode),
                () -> {
                    throw new FieldNotFoundException("Field not found");
                }
        );
    }

    @Override
    public List getAllFields() {
        return mapping.convertFieldListToFieldDTOList(fieldRepository.findAll());
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        Field existingField = fieldRepository.findById(fieldCode).orElseThrow(
                () -> new FieldNotFoundException("Field not found")
        );
        Field field = mapping.convertFieldDTOToField(fieldDTO);
        field.setFieldCode(existingField.getFieldCode());
        if (fieldDTO.getStaffIds() != null && !fieldDTO.getStaffIds().isEmpty()){
            List<Staff> staffList = staffRepository.findAllById(fieldDTO.getStaffIds());
            field.setStaff(staffList);
        }
        fieldRepository.save(field);
    }
}
