package tn.esprit.EldSync.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
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
    private List<HealthAlerts> healthAlerts = new ArrayList<>();

}
