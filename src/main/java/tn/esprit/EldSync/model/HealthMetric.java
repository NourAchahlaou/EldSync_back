package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "elderly_health_metrics")
public class HealthMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "observer_name")
    private String observerName;

    @Column(name = "cholesterol_lvl")
    private Integer cholesterolLvl;

    @Column(name = "blood_glucose_lvl")
    private Integer bloodGlucoseLvl;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @Column(name = "date")
    private Date date;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "health_metric_id")
    private List<HealthAlerts> healthAlerts;
}

