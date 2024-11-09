package lk.ijse.springboot.greenshadow.service;

import lk.ijse.springboot.greenshadow.customObj.FieldResponse;
import lk.ijse.springboot.greenshadow.customObj.StaffResponse;
import lk.ijse.springboot.greenshadow.dto.impl.FieldDTO;
import lk.ijse.springboot.greenshadow.entity.Field;
import lk.ijse.springboot.greenshadow.entity.Staff;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    FieldResponse getFieldByFieldCode(String fieldCode);
    void deleteField(String fieldCode);
    List getAllFields();
    void updateField(String fieldCOde, FieldDTO fieldDTO);
}
