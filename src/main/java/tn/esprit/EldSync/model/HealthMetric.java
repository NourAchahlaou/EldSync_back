package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "healthMetric")
public class HealthMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int bloodPressure;
    private int heartRate;
    private int bloodSugar;
    private float weight;
    private float height;

}
