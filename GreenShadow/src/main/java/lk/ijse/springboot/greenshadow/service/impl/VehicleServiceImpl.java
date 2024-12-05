package lk.ijse.springboot.greenshadow.service.impl;

import lk.ijse.springboot.greenshadow.customObj.VehicleResponse;
import lk.ijse.springboot.greenshadow.customObj.impl.VehicleErrorResponse;
import lk.ijse.springboot.greenshadow.dto.impl.VehicleDTO;
import lk.ijse.springboot.greenshadow.entity.Staff;
import lk.ijse.springboot.greenshadow.entity.Vehicle;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.StaffNotFoundException;
import lk.ijse.springboot.greenshadow.exception.VehicleNotFoundException;
import lk.ijse.springboot.greenshadow.repository.StaffRepository;
import lk.ijse.springboot.greenshadow.repository.VehicleRepository;
import lk.ijse.springboot.greenshadow.service.VehicleService;
import lk.ijse.springboot.greenshadow.util.AppUtil;
import lk.ijse.springboot.greenshadow.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final Mapping mapping;
    private final VehicleRepository vehicleRepository;
    private final StaffRepository staffRepository;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        vehicleDTO.setVehicleCode(AppUtil.generateVehicleCode());
        Vehicle save = vehicleRepository.save(mapping.convertVehicleDTOToVehicle(vehicleDTO));
        if (save.getVehicleCode() == null){
            throw new DataPersistFailedException("Failed to save vehicle data");
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleCode);
        if (vehicle.isEmpty()){
            throw new VehicleNotFoundException("Vehicle not found");
        }else {
            vehicleRepository.deleteById(vehicleCode);
        }
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO, String staffId , String vehicleCode) {

        Vehicle vehicle = vehicleRepository.findById(vehicleCode)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));

        Staff staff = null;
        if (!staffId.equals("N/A")) {

            staff = staffRepository.findById(staffId)
                    .orElseThrow(() -> new StaffNotFoundException("Staff not found"));
            vehicle.setStaff(staff);
        }

        vehicle.setLicensePlateNumber(vehicleDTO.getLicensePlateNumber());
        vehicle.setVehicleCategory(vehicleDTO.getVehicleCategory());
        vehicle.setFuelType(vehicleDTO.getFuelType());
        vehicle.setStatus(vehicleDTO.getStatus());
        vehicle.setRemarks(vehicleDTO.getRemarks());

        if (staff != null) {
            vehicle.setStaff(staff);
        } else {
            vehicle.setStaff(null);
        }

        vehicleRepository.save(vehicle);
    }

    @Override
    public VehicleResponse getSelectedVehicle(String vehicleCode) {
        if (vehicleRepository.existsById(vehicleCode)){
            return mapping.convertVehicleToVehicleDTO(vehicleRepository.getById(vehicleCode));
        }else {
            return new VehicleErrorResponse(404, "Vehicle not found");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicle() {
        return mapping.convertVehicleListToVehicleDTOList(vehicleRepository.findAll());
    }
}
