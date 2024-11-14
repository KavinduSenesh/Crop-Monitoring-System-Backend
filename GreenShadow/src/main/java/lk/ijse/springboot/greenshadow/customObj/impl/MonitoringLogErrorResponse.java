package lk.ijse.springboot.greenshadow.customObj.impl;

import lk.ijse.springboot.greenshadow.customObj.MonitoringLogResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogErrorResponse implements MonitoringLogResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
