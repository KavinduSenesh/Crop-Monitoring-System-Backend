package lk.ijse.springboot.greenshadow.service;

import lk.ijse.springboot.greenshadow.dto.impl.FieldDTO;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    void getFieldByFieldCode(String fieldCode);
    void deleteField(String fieldCode);
    void getAllFields();
    void updateField(FieldDTO fieldDTO);
}
