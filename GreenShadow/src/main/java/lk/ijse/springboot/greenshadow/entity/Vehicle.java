package lk.ijse.springboot.greenshadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
@Entity(name = "vehicle")
public class Vehicle {
    @Id
    @Column(name = "vehicle_code")
    private String vehicleCode;
    @Column(name = "license_plate_number")
    private String licensePlateNumber;
    @Column(name = "vehicle_category")
    private String vehicleCategory;
    @Column(name = "fuel_type")
    private String fuelType;
    @Column(name = "status")
    private String status;
    @Column(name = "remarks")
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "staff_member_id", referencedColumnName = "staff_member_id")
    private Staff staff;
}
