package lk.ijse.springboot.greenshadow.service.impl;

import lk.ijse.springboot.greenshadow.dto.impl.EquipmentDTO;
import lk.ijse.springboot.greenshadow.entity.Equipment;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.EquipmentNotFoundException;
import lk.ijse.springboot.greenshadow.repository.EquipmentRepository;
import lk.ijse.springboot.greenshadow.service.EquipmentService;
import lk.ijse.springboot.greenshadow.util.AppUtil;
import lk.ijse.springboot.greenshadow.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        equipmentDTO.setEquipmentId(AppUtil.generateEquipmentId());
        Equipment saved = equipmentRepository.save(mapping.convertEquipmentDTOToEquipment(equipmentDTO));
        if (saved.getEquipmentId() == null) {
            throw new DataPersistFailedException("Failed to save equipment data!");
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<Equipment> equipment = equipmentRepository.findById(equipmentId);
        if (equipment.isEmpty()){
            throw new EquipmentNotFoundException("Equipment not found");
        }else {
            equipmentRepository.deleteById(equipmentId);
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        equipmentRepository.findById(equipmentId).ifPresentOrElse(
                selectedEquipment -> {
                    equipmentDTO.setEquipmentId(selectedEquipment.getEquipmentId());
                    equipmentRepository.save(mapping.convertEquipmentDTOToEquipment(equipmentDTO));
                }, () -> {
                    throw new EquipmentNotFoundException("Equipment not found");
                }
        );
    }

    @Override
    public EquipmentDTO getSelectedEquipment(String equipmentId) {
        if (equipmentRepository.existsById(equipmentId)) {
            return mapping.convertEquipmentToEquipmentDTO(equipmentRepository.getById(equipmentId));
        } else {
            throw new EquipmentNotFoundException("Equipment not found");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        return mapping.convertEquipmentListToEquipmentDTOList(equipmentRepository.findAll());
    }

}
