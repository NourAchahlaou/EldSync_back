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
    private int id;

    @Column(name = "observer_name")
    private String observerName;

    @Column(name = "cholesterol_lvl")
    private int cholesterolLvl;

    @Column(name = "blood_glucose_lvl")
    private int bloodGlucoseLvl;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @Column(name = "date")
    private Date date;
}

