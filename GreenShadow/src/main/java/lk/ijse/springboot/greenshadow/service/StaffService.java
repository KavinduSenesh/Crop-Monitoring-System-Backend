package lk.ijse.springboot.greenshadow.service;

import lk.ijse.springboot.greenshadow.customObj.StaffResponse;
import lk.ijse.springboot.greenshadow.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    StaffResponse getStaffById(String id);
    List<StaffDTO> getAllStaff();
    void deleteStaff(String id);
    void updateStaff(String id, StaffDTO staffDTO);
}
