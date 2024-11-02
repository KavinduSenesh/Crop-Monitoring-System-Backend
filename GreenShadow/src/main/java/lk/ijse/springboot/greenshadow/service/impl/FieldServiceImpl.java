package lk.ijse.springboot.greenshadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springboot.greenshadow.dto.impl.FieldDTO;
import lk.ijse.springboot.greenshadow.repository.FieldRepository;
import lk.ijse.springboot.greenshadow.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {
    @Override
    public void saveField(FieldDTO fieldDTO) {

    }

    @Override
    public void getFieldByFieldCode(String fieldCode) {

    }

    @Override
    public void deleteField(String fieldCode) {

    }

    @Override
    public void getAllFields() {

    }

    @Override
    public void updateField(FieldDTO fieldDTO) {

    }
}
