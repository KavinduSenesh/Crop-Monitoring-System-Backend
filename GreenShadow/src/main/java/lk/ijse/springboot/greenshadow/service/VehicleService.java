package lk.ijse.springboot.greenshadow.service;

import jakarta.validation.Valid;
import lk.ijse.springboot.greenshadow.customObj.VehicleResponse;
import lk.ijse.springboot.greenshadow.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(@Valid VehicleDTO vehicleDTO);
    void deleteVehicle(String vehicleCode);
    void updateVehicle(@Valid VehicleDTO vehicleDTO, String staffId, String vehicleCode);
    VehicleResponse getSelectedVehicle(String vehicleCode);
    List<VehicleDTO> getAllVehicle();
}
