package lk.ijse.springboot.greenshadow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.ijse.springboot.greenshadow.customObj.StaffResponse;
import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO, StaffResponse {
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private String joinedDate;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dateOfBirth;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    private String email;
    private String role;
//    private List<String> fieldCodes;
//    private List<String> vehicleCodes;
//    private String equipmentId;
//    private List<String> monitoringLogCodes;
}
