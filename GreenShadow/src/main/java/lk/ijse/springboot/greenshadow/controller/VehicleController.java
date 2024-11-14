package lk.ijse.springboot.greenshadow.controller;

import jakarta.validation.Valid;
import lk.ijse.springboot.greenshadow.customObj.VehicleResponse;
import lk.ijse.springboot.greenshadow.customObj.impl.VehicleErrorResponse;
import lk.ijse.springboot.greenshadow.dto.impl.VehicleDTO;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.VehicleNotFoundException;
import lk.ijse.springboot.greenshadow.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(@Valid @RequestBody VehicleDTO vehicleDTO){
        if (vehicleDTO == null){
            logger.warn("Invalid request: Vehicle object is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try{
                vehicleService.saveVehicle(vehicleDTO);
                logger.info("Vehicle with vehicle code: {} saved successfully", vehicleDTO.getVehicleCode());
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailedException e){
                logger.error("Failed to save vehicle: {}", vehicleDTO, e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                logger.error("Internal server error while saving vehicle: {}", vehicleDTO, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping(value = "/{vehicle_code}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicle_code") String vehicleCode) {
        try {
            if (vehicleCode == null || vehicleCode.isEmpty()) {
                logger.warn("Vehicle code is empty or null");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vehicleService.deleteVehicle(vehicleCode);
            logger.info("Vehicle with vehicle code: {} deleted successfully", vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (VehicleNotFoundException e){
            logger.error("Vehicle with vehicle code: {} not found for deletion", vehicleCode);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error("Internal server error while deleting vehicle with vehicle code: {}", vehicleCode, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{vehicle_code}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateVehicle(@PathVariable("vehicle_code") String vehicleCode,@Valid @RequestBody VehicleDTO vehicleDTO){
        try {
            if (vehicleCode == null || vehicleCode.isEmpty()){
                logger.warn("Vehicle code is empty or null");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vehicleService.updateVehicle(vehicleCode, vehicleDTO);
            logger.info("Vehicle with vehicle code: {} updated successfully", vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (VehicleNotFoundException e){
            logger.error("Vehicle with vehicle code: {} not found for update", vehicleCode);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error("Internal server error while updating vehicle with vehicle code: {}", vehicleCode, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{vehicle_code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleResponse getSelectedVehicle(@PathVariable ("vehicle_code") String vehicleCode){
        logger.info("vehicle with vehicle code: {} selected", vehicleCode);
        return vehicleService.getSelectedVehicle(vehicleCode);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicles(){
        logger.info("All vehicles selected");
        return vehicleService.getAllVehicle();
    }
}
