package lk.ijse.springboot.greenshadow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "monitoring_log")
@Entity
public class MonitoringLog implements SuperEntity {
    @Id
    @Column(name = "log_code")
    private String logCode;
    @Column(name = "log_date")
    private Date logDate;
    @Column(name = "observation")
    private String observation;
    @Column(name = "observed_image", columnDefinition = "LONGTEXT")
    private String observedImage;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "monitoring_log_field",
        joinColumns = @JoinColumn(name = "field_code"),
        inverseJoinColumns = @JoinColumn(name = "log_code")
    )
    private List<Field> field;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "monitoring_log_crop",
            joinColumns = @JoinColumn(name = "log_code"),
            inverseJoinColumns = @JoinColumn(name = "crop_code")
    )
    private List<Crop> crop;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "monitoring_log_staff",
            joinColumns = @JoinColumn(name = "log_code"),
            inverseJoinColumns = @JoinColumn(name = "staff_member_id")
    )
    private List<Staff> staff;
}
