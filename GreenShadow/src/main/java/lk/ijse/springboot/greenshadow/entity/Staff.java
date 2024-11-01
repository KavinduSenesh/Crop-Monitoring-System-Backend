package lk.ijse.springboot.greenshadow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @Column(name = "staff_member_id")
    private String staffMemberId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "designation")
    private String designation;
    @Enumerated
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "joined_date")
    private String joinedDate;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "address_line_1")
    private String addressLine1;
    @Column(name = "address_line_2")
    private String addressLine2;
    @Column(name = "address_line_3")
    private String addressLine3;
    @Column(name = "address_line_4")
    private String addressLine4;
    @Column(name = "address_line_5")
    private String addressLine5;
    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "email")
    private String email;
    @Enumerated
    @Column(name = "role")
    private Role role;

    @ManyToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Field> field;

    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Vehicle> vehicle;

    @OneToOne(mappedBy = "staff",optional = true)
    @JsonIgnore
    private Equipment equipment;

    @ManyToMany(mappedBy = "staff")
    @JsonIgnore
    private List<MonitoringLog> monitoringLogs;
}
