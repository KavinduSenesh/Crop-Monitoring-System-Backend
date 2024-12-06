package lk.ijse.springboot.greenshadow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "vehicle")
@Entity
public class Vehicle implements SuperEntity {
    @Id
    @Column(name = "vehicle_code")
    private String vehicleCode;
    @Column(name = "license_plate_number", unique = true)
    private String licensePlateNumber;
    @Column(name = "vehicle_category")
    private String vehicleCategory;
    @Column(name = "fuel_type")
    private String fuelType;
    @Column(name = "status")
    private String status;
    @Column(name = "remarks")
    private String remarks;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "staff_member_id", referencedColumnName = "staff_member_id")
    private Staff staff;
}
