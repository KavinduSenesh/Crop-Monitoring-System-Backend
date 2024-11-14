package lk.ijse.springboot.greenshadow.dto.impl;

import jakarta.validation.constraints.*;
import lk.ijse.springboot.greenshadow.customObj.StaffResponse;
import lk.ijse.springboot.greenshadow.dto.SuperDTO;
import lk.ijse.springboot.greenshadow.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO, StaffResponse {
    private String staffId;
    @NotBlank
    @Size(max = 50)
    private String firstName;
    @NotBlank
    @Size(max = 50)
    private String lastName;
    @NotBlank
    private String designation;
    @NotBlank
    private String gender;
    @Past
    private Date joinedDate;
    @Past
    private Date dateOfBirth;
    @NotBlank
    private String addressLine1;
    @NotBlank
    private String addressLine2;
    @NotBlank
    private String addressLine3;
    @NotBlank
    private String addressLine4;
    @NotBlank
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$")
    private String addressLine5;
    @NotBlank
    private String contactNo;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String role;
    private List<String> fieldCodes;
    private List<String> vehicleCodes;
    private String equipmentId;
    private List<String> monitoringLogCodes;
}
