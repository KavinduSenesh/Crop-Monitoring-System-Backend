package lk.ijse.springboot.greenshadow.util;

import lk.ijse.springboot.greenshadow.dto.impl.CropDTO;
import lk.ijse.springboot.greenshadow.dto.impl.FieldDTO;
import lk.ijse.springboot.greenshadow.dto.impl.StaffDTO;
import lk.ijse.springboot.greenshadow.dto.impl.UserDTO;
import lk.ijse.springboot.greenshadow.entity.Crop;
import lk.ijse.springboot.greenshadow.entity.Field;
import lk.ijse.springboot.greenshadow.entity.Staff;
import lk.ijse.springboot.greenshadow.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.description.field.FieldList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper mapper;

    public User convertUserDTOToUser(UserDTO userDTO){
        return mapper.map(userDTO, User.class);
    }
    public UserDTO convertUserToUserDTO(User user){
        return mapper.map(user, UserDTO.class);
    }
    public Staff convertStaffDTOToStaff(StaffDTO staffDTO){
        return mapper.map(staffDTO, Staff.class);
    }
    public StaffDTO convertStaffToStaffDTO(Staff staff){
        return mapper.map(staff, StaffDTO.class);
    }
    public List convertStaffListToStaffDTOList(List<Staff> all){
        return mapper.map(all, List.class);
    }
    public Field convertFieldDTOToField(FieldDTO fieldDTO) {
        return mapper.map(fieldDTO, Field.class);
    }
    public FieldDTO convertFieldToFieldDTO(Field field) {
        return mapper.map(field, FieldDTO.class);
    }
    public List convertFieldListToFieldDTOList(List<Field> all){
        return mapper.map(all, List.class);
    }
    public Crop convertCropDTOToCrop(CropDTO cropDTO){
        return mapper.map(cropDTO, Crop.class);
    }
    public CropDTO convertCropToCropDTO(Crop crop){
        return mapper.map(crop, CropDTO.class);
    }
    public List convertCropListToCropDTOList(List<Crop> all){
        return mapper.map(all, List.class);
    }
}
