package lk.ijse.springboot.greenshadow.service.impl;

import lk.ijse.springboot.greenshadow.customObj.VehicleResponse;
import lk.ijse.springboot.greenshadow.customObj.impl.VehicleErrorResponse;
import lk.ijse.springboot.greenshadow.dto.impl.VehicleDTO;
import lk.ijse.springboot.greenshadow.entity.Vehicle;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.VehicleNotFoundException;
import lk.ijse.springboot.greenshadow.repository.VehicleRepository;
import lk.ijse.springboot.greenshadow.service.VehicleService;
import lk.ijse.springboot.greenshadow.util.AppUtil;
import lk.ijse.springboot.greenshadow.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final Mapping mapping;
    private final VehicleRepository vehicleRepository;

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
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {
        vehicleRepository.findById(vehicleCode).ifPresentOrElse(
                selectedVehicle -> {
                    vehicleDTO.setVehicleCode(selectedVehicle.getVehicleCode());
                    vehicleRepository.save(mapping.convertVehicleDTOToVehicle(vehicleDTO));
                }, () -> {
                    throw new VehicleNotFoundException("Vehicle not found");
                }
        );
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
