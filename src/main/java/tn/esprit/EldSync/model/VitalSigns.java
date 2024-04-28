package tn.esprit.EldSync.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vitalSigns")
public class VitalSigns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int oxygenSaturation;
    private int temperature;
    private int bloodSugar;
    private float respiratoryRate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("elder") // Ignore elder property during serialization
    private List<HealthAlerts> healthAlerts = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties("vitalSigns") // Ignore vitalSigns property during serialization
    private Profile elder;

    @ManyToOne
    @JsonIgnoreProperties("vitalSigns") // Ignore vitalSigns property during serialization
    private Profile measurementTaker;

}
