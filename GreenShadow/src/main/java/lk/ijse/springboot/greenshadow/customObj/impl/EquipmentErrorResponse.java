package lk.ijse.springboot.greenshadow.customObj.impl;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentErrorResponse {
    private int ErrorCode;
    private String ErrorMessage;
}
