package lk.ijse.springboot.greenshadow.util;

import lk.ijse.springboot.greenshadow.dto.impl.*;
import lk.ijse.springboot.greenshadow.entity.*;
import lk.ijse.springboot.greenshadow.repository.FieldRepository;
import lk.ijse.springboot.greenshadow.repository.StaffRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.description.field.FieldList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    private static FieldRepository fieldRepository;
    private static StaffRepository staffRepository;

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

    public Vehicle convertVehicleDTOToVehicle(VehicleDTO vehicleDTO){
        return mapper.map(vehicleDTO, Vehicle.class);
    }
    public VehicleDTO convertVehicleToVehicleDTO(Vehicle vehicle){
        return mapper.map(vehicle, VehicleDTO.class);
    }
    public List convertVehicleListToVehicleDTOList(List<Vehicle> all){
        return mapper.map(all, List.class);
    }

    public Equipment convertEquipmentDTOToEquipment(EquipmentDTO equipmentDTO){
        return mapper.map(equipmentDTO, Equipment.class);
    }
    public EquipmentDTO convertEquipmentToEquipmentDTO(Equipment equipment){
        return mapper.map(equipment, EquipmentDTO.class);
    }
    public List convertEquipmentListToEquipmentDTOList(List<Equipment> all){
        return mapper.map(all, List.class);
    }

    public MonitoringLog convertMonitoringLogDTOToMonitoringLog(MonitoringLogDTO monitoringLogDTO){
        return mapper.map(monitoringLogDTO, MonitoringLog.class);
    }
    public MonitoringLogDTO convertMonitoringLogToMonitoringLogDTO(MonitoringLog monitoringLog){
        return mapper.map(monitoringLog, MonitoringLogDTO.class);
    }
    public List convertMonitoringLogListToConvertMonitoringLogDTOList(List<MonitoringLog> all){
        return mapper.map(all, List.class);
    }
}
