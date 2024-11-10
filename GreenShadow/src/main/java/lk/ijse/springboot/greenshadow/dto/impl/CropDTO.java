package lk.ijse.springboot.greenshadow.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lk.ijse.springboot.greenshadow.customObj.CropResponse;
import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lk.ijse.springboot.greenshadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements SuperDTO, CropResponse {
    private String cropCode;
    @NotBlank
    @Size(max = 50)
    private String cropCommonName;
    @NotBlank
    @Size(max = 50)
    private String cropScientificName;
    private String cropImage;
    @NotBlank
    private String category;
    @NotBlank
    private String cropSeason;
    @NotBlank
    private String fieldCode;
    private List<String> monitoringLogCodes;
}
