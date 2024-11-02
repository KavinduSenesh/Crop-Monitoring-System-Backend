package lk.ijse.springboot.greenshadow.service;

import jakarta.transaction.Transactional;
import lk.ijse.springboot.greenshadow.customObj.StaffErrorResponse;
import lk.ijse.springboot.greenshadow.customObj.StaffResponse;
import lk.ijse.springboot.greenshadow.dto.StaffDTO;
import lk.ijse.springboot.greenshadow.entity.Staff;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.NotFoundException;
import lk.ijse.springboot.greenshadow.repository.StaffRepository;
import lk.ijse.springboot.greenshadow.util.AppUtil;
import lk.ijse.springboot.greenshadow.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            staffRepository.deleteById(id);
        }else {
            throw new NotFoundException("Staff not found");
        }
    }

    @Override
    public void updateStaff(StaffDTO staffDTO) {
        Optional<Staff> staff = staffRepository.findById(staffDTO.getStaffId());
        if (staff.isPresent()){
            Staff save = staffRepository.save(mapping.convertStaffDTOToStaff(staffDTO));
            if (save == null){
                throw new DataPersistFailedException("Staff update failed");
            }
        }else {
            throw new NotFoundException("Staff not found");
        }
    }
}
