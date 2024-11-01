package lk.ijse.springboot.greenshadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
@Entity(name = "equipment")
public class Equipment {
    @Id
    @Column(name = "equipment_id")
    private String equipmentId;
    @Column(name = "equipment_name")
    private String equipmentName;
    @Column(name = "equipment_type")
    private EquipmentType equipmentType;
    @Column(name = "availability_status")
    private Status availabilityStatus;

    @OneToOne(optional = true)
    @JoinColumn(name = "staff_member_id", referencedColumnName = "staff_member_id")
    private Staff staff;

    @ManyToOne(optional = true)
    @JoinColumn(name = "field_code", referencedColumnName = "field_code")
    private Field field;

}
