package lk.ijse.springboot.greenshadow.dto.impl;

import jakarta.validation.constraints.NotBlank;
import lk.ijse.springboot.greenshadow.customObj.MonitoringLogResponse;
import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lk.ijse.springboot.greenshadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDTO implements SuperDTO, MonitoringLogResponse {
    private String monitoringLogCode;
    private Date logDate;
    @NotBlank
    private String observation;
    private String observedImage;
    private List<String> fieldCodes;
    private List<String> cropCodes;
    private List<String> staffIds;
}
