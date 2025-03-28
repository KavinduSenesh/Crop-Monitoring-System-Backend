package lk.ijse.springboot.greenshadow.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lk.ijse.springboot.greenshadow.customObj.FieldResponse;
import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lk.ijse.springboot.greenshadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO, FieldResponse {
    private String fieldCode;
    @NotBlank
    @Size(max = 50)
    private String fieldName;
    @NotNull
    private Point fieldLocation;
    @Positive
    private double fieldSize;
    private String fieldImage1;
    private String fieldImage2;
    private List<String> cropCodes;
    private List<String> staffIds;
    private List<String> equipmentIds;
    private List<String> monitoringLogCodes;
}
