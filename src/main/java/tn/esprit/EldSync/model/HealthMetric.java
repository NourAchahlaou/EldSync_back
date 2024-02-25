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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthAlerts> healthAlerts = new ArrayList<>();

}

