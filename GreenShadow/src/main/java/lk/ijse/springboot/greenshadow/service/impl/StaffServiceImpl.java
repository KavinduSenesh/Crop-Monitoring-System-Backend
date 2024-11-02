package lk.ijse.springboot.greenshadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springboot.greenshadow.customObj.impl.StaffErrorResponse;
import lk.ijse.springboot.greenshadow.customObj.StaffResponse;
import lk.ijse.springboot.greenshadow.dto.impl.StaffDTO;
import lk.ijse.springboot.greenshadow.entity.Staff;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.NotFoundException;
import lk.ijse.springboot.greenshadow.repository.StaffRepository;
import lk.ijse.springboot.greenshadow.service.StaffService;
import lk.ijse.springboot.greenshadow.util.AppUtil;
import lk.ijse.springboot.greenshadow.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    private final Mapping mapping;

    @Override
    public void saveStaff(StaffDTO staffDTO) {
        String staffId = AppUtil.generateStaffId();
        while (staffRepository.existsById(staffId)){
            staffId = AppUtil.generateStaffId();
        }
        staffDTO.setStaffId(staffId);
        Staff save = staffRepository.save(mapping.convertStaffDTOToStaff(staffDTO));
        if (save == null){
            throw new DataPersistFailedException("Staff save failed");
        }
    }

    @Override
    public StaffResponse getStaffById(String id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isPresent()){
            return mapping.convertStaffToStaffDTO(staff.get());
        }else {
            return new StaffErrorResponse(404, "Staff not found");
        }
    }

    @Override
    public List getAllStaff() {
        return mapping.convertStaffListToStaffDTOList(staffRepository.findAll());
    }

    @Override
    public void deleteStaff(String id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isPresent()){
            staffRepository.delete(staff.get());
        }else {
            throw new NotFoundException("Staff not found");
        }
    }

    @Override
    public void updateStaff(String id, StaffDTO staffDTO) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isPresent()) {
            staffDTO.setStaffId(id);
            staffRepository.save(mapping.convertStaffDTOToStaff(staffDTO));
        } else {
            throw new NotFoundException("Staff not found");
        }
    }
}

