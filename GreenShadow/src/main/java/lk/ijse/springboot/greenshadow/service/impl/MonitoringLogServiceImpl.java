package lk.ijse.springboot.greenshadow.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springboot.greenshadow.customObj.MonitoringLogResponse;
import lk.ijse.springboot.greenshadow.dto.impl.MonitoringLogDTO;
import lk.ijse.springboot.greenshadow.entity.Crop;
import lk.ijse.springboot.greenshadow.entity.Field;
import lk.ijse.springboot.greenshadow.entity.MonitoringLog;
import lk.ijse.springboot.greenshadow.entity.Staff;
import lk.ijse.springboot.greenshadow.exception.DataPersistFailedException;
import lk.ijse.springboot.greenshadow.exception.MonitoringLogNotFoundException;
import lk.ijse.springboot.greenshadow.repository.CropRepository;
import lk.ijse.springboot.greenshadow.repository.FieldRepository;
import lk.ijse.springboot.greenshadow.repository.MonitoringLogRepository;
import lk.ijse.springboot.greenshadow.repository.StaffRepository;
import lk.ijse.springboot.greenshadow.service.MonitoringLogService;
import lk.ijse.springboot.greenshadow.util.AppUtil;
import lk.ijse.springboot.greenshadow.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MonitoringLogServiceImpl implements MonitoringLogService {

    private final MonitoringLogRepository monitoringLogRepository;

    private final Mapping mapping;
    private final FieldRepository fieldRepository;
    private final CropRepository cropRepository;
    private final StaffRepository staffRepository;

    @Override
    public void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO) {
        monitoringLogDTO.setMonitoringLogCode(AppUtil.generateMonitoringLogCode());
        monitoringLogDTO.setLogDate(AppUtil.getCurrentDataAndTime());
        MonitoringLog monitoringLog = mapping.convertMonitoringLogDTOToMonitoringLog(monitoringLogDTO);

        List<Field> fieldList = !monitoringLogDTO.getFieldCodes().isEmpty()
                ? fieldRepository.findAllById(monitoringLogDTO.getFieldCodes())
                : Collections.emptyList();
        monitoringLog.setField(fieldList);

        List<Crop> cropList = !monitoringLogDTO.getCropCodes().isEmpty()
                ? cropRepository.findAllById(monitoringLogDTO.getCropCodes())
                : Collections.emptyList();
        monitoringLog.setCrop(cropList);

        List<Staff> staffList = !monitoringLogDTO.getStaffIds().isEmpty()
                ? staffRepository.findAllById(monitoringLogDTO.getStaffIds())
                : Collections.emptyList();
        monitoringLog.setStaff(staffList);

        MonitoringLog saved = monitoringLogRepository.save(monitoringLog);
        if (saved.getLogCode() == null){
            throw new DataPersistFailedException("Failed to save monitoring log data!");
        }
    }

    @Override
    public void deleteMonitoringLog(String monitoringLogCode) {
        monitoringLogRepository.findById(monitoringLogCode).ifPresentOrElse(
                monitoringLog -> monitoringLogRepository.deleteById(monitoringLogCode)
                , () -> {
                    throw new MonitoringLogNotFoundException("Monitoring Log not found");
                }
        );
    }

    @Override
    public void updateMonitoringLog(String monitoringLogCode, MonitoringLogDTO monitoringLogDTO) {
        monitoringLogDTO.setLogDate(AppUtil.getCurrentDataAndTime());
        MonitoringLog savedMonitoringLog = monitoringLogRepository.findById(monitoringLogCode)
                .orElseThrow(() -> new MonitoringLogNotFoundException("Monitoring Log not found"));
        MonitoringLog newMonitoringLogs = mapping.convertMonitoringLogDTOToMonitoringLog(monitoringLogDTO);
                newMonitoringLogs.setLogCode(savedMonitoringLog.getLogCode());

                List<Field> fieldList = !monitoringLogDTO.getFieldCodes().isEmpty()
                        ? fieldRepository.findAllById(monitoringLogDTO.getFieldCodes())
                        : Collections.emptyList();
                newMonitoringLogs.setField(fieldList);

                List<Crop> cropList = !monitoringLogDTO.getCropCodes().isEmpty()
                        ? cropRepository.findAllById(monitoringLogDTO.getCropCodes())
                        : Collections.emptyList();
                newMonitoringLogs.setCrop(cropList);

                List<Staff> staffList = !monitoringLogDTO.getStaffIds().isEmpty()
                        ? staffRepository.findAllById(monitoringLogDTO.getStaffIds())
                        : Collections.emptyList();
                newMonitoringLogs.setStaff(staffList);

                monitoringLogRepository.save(newMonitoringLogs);
    }

    @Override
    public MonitoringLogResponse getSelectedMonitoringLog(String monitoringLogCode) {
        return monitoringLogRepository.findById(monitoringLogCode)
                .map(log -> {
                    MonitoringLogDTO monitoringLogDTO = mapping.convertMonitoringLogToMonitoringLogDTO(log);

                    monitoringLogDTO.setFieldCodes(log.getField().stream()
                            .map(Field::getFieldCode)
                            .collect(Collectors.toList()));

                    monitoringLogDTO.setCropCodes(log.getCrop().stream()
                            .map(Crop::getCropCode)
                            .collect(Collectors.toList()));

                    monitoringLogDTO.setStaffIds(log.getStaff().stream()
                            .map(Staff::getStaffId)
                            .collect(Collectors.toList()));
                    return monitoringLogDTO;
                })
                .orElseThrow(() -> new MonitoringLogNotFoundException("Monitoring Log not found"));
    }

    @Override
    public List getAllMonitoringLogs() {
        return mapping.convertMonitoringLogListToConvertMonitoringLogDTOList(monitoringLogRepository.findAll());
    }
}

