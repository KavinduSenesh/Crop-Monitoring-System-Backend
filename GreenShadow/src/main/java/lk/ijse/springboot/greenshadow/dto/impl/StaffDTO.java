package lk.ijse.springboot.greenshadow.dto.impl;

import lk.ijse.springboot.greenshadow.customObj.StaffResponse;
import lk.ijse.springboot.greenshadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO, StaffResponse {
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;
    private String joinedDate;
    private String dateOfBirth;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    private String email;
    private String role;
    private List<String> fieldCodes;
    private List<String> vehicleCodes;
    private String equipmentId;
    private List<String> monitoringLogCodes;
}
