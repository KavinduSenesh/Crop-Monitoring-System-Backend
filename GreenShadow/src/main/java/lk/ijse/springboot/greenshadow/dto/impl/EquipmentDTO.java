package lk.ijse.springboot.greenshadow.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lk.ijse.springboot.greenshadow.customObj.EquipmentResponse;
import lk.ijse.springboot.greenshadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements SuperDTO, EquipmentResponse {
    private String equipmentId;
    @NotBlank
    @Size(max = 50)
    private String equipmentName;
    @NotBlank
    @Pattern(regexp = "(?i)^(Electrical|Mechanical)$")
    private String equipmentType;
    @NotBlank
    @Pattern(regexp = "^(?i)(Available|Not_Available)$")
    private String availabilityStatus;
    private String staffId;
    private String fieldCode;
}
