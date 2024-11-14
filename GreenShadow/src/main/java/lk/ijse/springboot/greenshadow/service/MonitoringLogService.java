package lk.ijse.springboot.greenshadow.service;

import jakarta.validation.Valid;
import lk.ijse.springboot.greenshadow.customObj.MonitoringLogResponse;
import lk.ijse.springboot.greenshadow.dto.impl.MonitoringLogDTO;

import java.util.List;

public interface MonitoringLogService {
    void saveMonitoringLog(@Valid MonitoringLogDTO monitoringLogDTO);
    void deleteMonitoringLog(String monitoringLogCode);
    void updateMonitoringLog(String monitoringLogCode, @Valid MonitoringLogDTO monitoringLogDTO);
    MonitoringLogResponse getSelectedMonitoringLog(String monitoringLogCode);
    List<MonitoringLogDTO> getAllMonitoringLogs();
}
