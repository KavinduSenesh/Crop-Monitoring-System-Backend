package lk.ijse.springboot.greenshadow.dto.impl;

import lk.ijse.springboot.greenshadow.customObj.UserResponse;
import lk.ijse.springboot.greenshadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements SuperDTO, UserResponse {
    private String equipmentId;
    private String name;
    private String type;
    private String status;
    private String staffId;
    private String fieldCode;
}
