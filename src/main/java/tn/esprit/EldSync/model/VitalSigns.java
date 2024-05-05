package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "elderly_Vital_signs")
public class VitalSigns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "observer_name")
    private String nameOfObserver;

    @Column(name = "oxygen_saturation")
    private int oxygenSaturation;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "heart_rate")
    private int heartRate;

    @Column(name = "respiratory_rate")
    private int respiratoryRate;

    @Column(name = "elder_name")
    private String nameOfElder;

    @Column(name = "measurement_date")
    private Date date;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vital_signs_id")
    private List<HealthAlerts> healthAlerts;
}




