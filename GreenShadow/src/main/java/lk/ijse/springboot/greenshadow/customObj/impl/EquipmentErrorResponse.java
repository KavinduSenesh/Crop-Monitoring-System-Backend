package lk.ijse.springboot.greenshadow.customObj.impl;

import lk.ijse.springboot.greenshadow.customObj.EquipmentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentErrorResponse implements EquipmentResponse, Serializable {
    int errorCode;
    String errorMessage;
}
