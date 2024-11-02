package lk.ijse.springboot.greenshadow.controller;

import lk.ijse.springboot.greenshadow.customObj.StaffResponse;
import lk.ijse.springboot.greenshadow.dto.StaffDTO;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.NotFoundException;
import lk.ijse.springboot.greenshadow.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/staff")
public class StaffController {

    private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<?> saveStaff(@RequestBody StaffDTO staffDTO) {
        logger.info("Attempting to save staff: {}", staffDTO);
        try {
            staffService.saveStaff(staffDTO);
            logger.info("Successfully saved staff with ID: {}", staffDTO.getStaffId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            logger.warn("Failed to save staff: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while saving staff: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffResponse> getStaffById(@PathVariable String id) {
        logger.info("Fetching staff with ID: {}", id);
        try {
            StaffResponse staffResponse = staffService.getStaffById(id);
            logger.info("Successfully fetched staff with ID: {}", id);
            return new ResponseEntity<>(staffResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            logger.warn("Failed to fetch staff: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while fetching staff: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllStaff() {
        logger.info("Fetching all staff");
        return new ResponseEntity<>(staffService.getAllStaff(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable String id) {
        logger.info("Attempting to delete staff with ID: {}", id);
        try {
            staffService.deleteStaff(id);
            logger.info("Successfully deleted staff with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            logger.warn("Failed to delete staff: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error("Unexpected error occurred while deleting staff: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<?> updateStaff(@RequestBody StaffDTO staffDTO){
        logger.info("Attempting to update staff: {}", staffDTO);
        try{
            staffService.updateStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (DataPersistFailedException | NotFoundException e){
            logger.error("Failed to update staff: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error("Unexpected error occurred while updating staff: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
