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
@Table(name = "crops")
public class Crop implements SuperEntity {
    @Id
    @Column(name = "crop_code")
    private String cropCode;
    @Column(name = "crop_common_name")
    private String cropCommonName;
    @Column(name = "crop_scientific_name")
    private String cropScientificName;
    @Column(name = "crop_image", columnDefinition = "LONGTEXT")
    private String cropImage;
    @Column(name = "category")
    private String category;
    @Column(name = "crop_season")
    private String cropSeason;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "field_code", referencedColumnName = "field_code")
    private Field field;

    @ManyToMany(mappedBy = "crop")
    private List<MonitoringLog> monitoringLogList;
}
