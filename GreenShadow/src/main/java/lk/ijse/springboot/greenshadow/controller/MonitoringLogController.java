package lk.ijse.springboot.greenshadow.controller;

import jakarta.validation.Valid;
import lk.ijse.springboot.greenshadow.customObj.MonitoringLogResponse;
import lk.ijse.springboot.greenshadow.dto.impl.MonitoringLogDTO;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.MonitoringLogNotFoundException;
import lk.ijse.springboot.greenshadow.service.MonitoringLogService;
import lk.ijse.springboot.greenshadow.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/monitoringLog")
@CrossOrigin
public class MonitoringLogController {

    private final MonitoringLogService monitoringLogService;

    static final Logger logger = LoggerFactory.getLogger(MonitoringLogController.class);
    private final ResourcePatternResolver resourcePatternResolver;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveMonitoringLog(
                @Valid @RequestParam("observation") String observation,
                @Valid @RequestParam("observedImage") MultipartFile observedImage,
                @Valid @RequestParam("fieldCodes") List<String> fieldCodes,
                @Valid @RequestParam("cropCodes") List<String> cropCodes,
                @Valid @RequestParam("staffIds") List<String> staffIds
        ) {
        logger.info("Request Parameters: fieldCode={}, cropCode={}, staffIds={}", fieldCodes, cropCodes, staffIds);
        logger.info("Request received to save monitoring log with Observation: {}", observation);
        if (observation == null || observedImage == null || fieldCodes == null || cropCodes == null || staffIds == null) {
            logger.warn("Invalid request: Monitoring Log object or Observed Image is null ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            MonitoringLogDTO monitoringLogDTO = new MonitoringLogDTO();
            try {
                monitoringLogDTO.setObservation(observation);
                monitoringLogDTO.setObservedImage(AppUtil.toBase64Pic(observedImage));
                monitoringLogDTO.setFieldCodes(fieldCodes.isEmpty() ? null : fieldCodes);
                monitoringLogDTO.setCropCodes(cropCodes.isEmpty() ? null : cropCodes);
                monitoringLogDTO.setStaffIds(staffIds.isEmpty() ? null : staffIds);
                monitoringLogService.saveMonitoringLog(monitoringLogDTO);
                logger.info("Monitoring Log with Observation: {} saved successfully", monitoringLogDTO.getObservation());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (DataPersistFailedException e) {
                logger.error("Failed to save monitoring log: {}", monitoringLogDTO, e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.error("Internal server error while saving monitoring log: {}", monitoringLogDTO, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping(value = "/{monitoringLogCode}")
    public ResponseEntity<Void> deleteMonitoringLog(@PathVariable("monitoringLogCode") String monitoringLogCode){
        logger.info("Request received to delete monitoring log with Log Code: {}", monitoringLogCode);
        try{monitoringLogService.deleteMonitoringLog(monitoringLogCode);
            logger.info("Monitoring Log with Log Code: {} deleted successfully", monitoringLogCode);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (MonitoringLogNotFoundException e){
            logger.error("Monitoring Log not found with Log Code: {}", monitoringLogCode, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error("Internal server error while deleting monitoring log with Log Code: {}", monitoringLogCode, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{monitoringLogCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateMonitoringLog(
            @PathVariable("monitoringLogCode") String monitoringLog,
            @RequestParam("observation") String observation,
            @RequestParam("observedImage") MultipartFile observedImage,
            @RequestParam("fieldCodes") List<String> fieldCodes,
            @RequestParam("cropCodes") List<String> cropCodes,
            @RequestParam("staffIds") List<String> staffIds
    ){
        logger.info("Request received to update monitoring log with Log Code: {}", monitoringLog);
        if (observation == null || observedImage == null || fieldCodes == null || cropCodes == null || staffIds == null){
            logger.warn("Invalid request: Monitoring Log object or Observed Image is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            MonitoringLogDTO monitoringLogDTO = new MonitoringLogDTO();
            try{
                monitoringLogDTO.setObservation(observation);
                monitoringLogDTO.setObservedImage(AppUtil.toBase64Pic(observedImage));
                monitoringLogDTO.setFieldCodes(fieldCodes.isEmpty() ? null : fieldCodes);
                monitoringLogDTO.setCropCodes(cropCodes.isEmpty() ? null : cropCodes);
                monitoringLogDTO.setStaffIds(cropCodes.isEmpty() ? null : cropCodes);
            monitoringLogService.updateMonitoringLog(monitoringLog, monitoringLogDTO);
            logger.info("Monitoring Log with Log Code: {} updated successfully", monitoringLog);
            return new ResponseEntity<>(HttpStatus.OK);
            }catch (DataPersistFailedException e){
                logger.error("Failed to update monitoring log: {}", monitoringLogDTO, e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                logger.error("Internal server error while updating monitoring log: {}", monitoringLogDTO, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping(value = "/{monitoringLogCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringLogResponse getSelectedMonitoringLog(@PathVariable("monitoringLogCode") String monitoringLogCode){
        logger.info("Request received to get monitoring log with Log Code: {}", monitoringLogCode);
        return monitoringLogService.getSelectedMonitoringLog(monitoringLogCode);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDTO> getAllMonitoringLogs(){
        logger.info("Request received to get all monitoring logs");
        return monitoringLogService.getAllMonitoringLogs();
    }
}
