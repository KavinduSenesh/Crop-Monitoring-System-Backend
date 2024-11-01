package lk.ijse.springboot.greenshadow.dto;

import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO, UserResponse {
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private double fieldSize;
    private String fieldImage1;
    private String fieldImage2;
    private List<String> cropCodes;
    private List<String> staffIds;
    private List<String> equipmentIds;
    private List<String> monitoringLogCodes;
}