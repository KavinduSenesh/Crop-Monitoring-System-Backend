package lk.ijse.springboot.greenshadow.service;

import jakarta.validation.Valid;
import lk.ijse.springboot.greenshadow.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(@Valid EquipmentDTO equipmentDTO);
    void deleteEquipment(String equipmentId);
    void updateEquipment(String equipmentId, @Valid EquipmentDTO equipmentDTO);
    EquipmentDTO getSelectedEquipment(String equipmentId);
    List getAllEquipments();
}
