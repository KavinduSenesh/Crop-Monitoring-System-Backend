package lk.ijse.springboot.greenshadow.dto;

import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDTO implements SuperDTO, UserResponse {
    private String monitoringLogCode;
    private Date logDate;
    private String observation;
    private String observedImage;
    private List<String> fieldCodes;
    private List<String> cropCodes;
    private List<String> staffIds;
}
