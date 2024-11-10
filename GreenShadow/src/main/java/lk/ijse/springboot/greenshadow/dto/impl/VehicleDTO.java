package lk.ijse.springboot.greenshadow.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lk.ijse.springboot.greenshadow.customObj.VehicleResponse;
import lk.ijse.springboot.greenshadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements SuperDTO, VehicleResponse {
    private String vehicleCode;
    @NotBlank
    private String licensePlateNumber;
    @NotBlank
    private String vehicleCategory;
    @NotBlank
    private String fuelType;
    @NotBlank
    @Pattern(regexp = "(?i)^(Available|Unavailable|In Maintenance|Out of Service)$")
    private String status;
    @NotBlank
    @Pattern(regexp = "^(?!\\s*$).+|N/A")
    private String remarks;
    private String staffId;
}
