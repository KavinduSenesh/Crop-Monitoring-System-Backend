package lk.ijse.springboot.greenshadow.service.impl;

import lk.ijse.springboot.greenshadow.dto.impl.EquipmentDTO;
import lk.ijse.springboot.greenshadow.entity.Equipment;
import lk.ijse.springboot.greenshadow.entity.Field;
import lk.ijse.springboot.greenshadow.entity.Staff;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.EquipmentNotFoundException;
import lk.ijse.springboot.greenshadow.exception.FieldNotFoundException;
import lk.ijse.springboot.greenshadow.exception.StaffNotFoundException;
import lk.ijse.springboot.greenshadow.repository.EquipmentRepository;
import lk.ijse.springboot.greenshadow.repository.FieldRepository;
import lk.ijse.springboot.greenshadow.repository.StaffRepository;
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
    private final StaffRepository staffRepository;
    private final FieldRepository fieldRepository;

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
    public void updateEquipment(EquipmentDTO equipmentDTO , String staffId , String fieldCode , String equipmentId) {

        Equipment equipment = equipmentRepository.findById(equipmentId).orElse(null);

        if (equipment != null) {

            equipment = mapping.convertEquipmentDTOToEquipment(equipmentDTO);
            equipment.setEquipmentId(equipmentId);
            if (staffId.equals("N/A")) {
                equipment.setStaff(null);
            } else {
                Optional<Staff> optional = staffRepository.findById(staffId);
                if (optional.isPresent()) {
                    Staff staff = optional.get();
                    equipment.setStaff(staff);
                } else {
                    throw new StaffNotFoundException("Staff not found");
                }
            }

            if (fieldCode.equals("N/A")) {
                equipment.setField(null);
            } else {
                Optional<Field> optional = fieldRepository.findById(fieldCode);
                if (optional.isPresent()) {
                    Field field = optional.get();
                    equipment.setField(field);
                } else {
                    throw new FieldNotFoundException("Field not found");
                }
            }
        }
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
