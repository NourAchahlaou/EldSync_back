package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "healthMetric")
public class HealthAlerts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private AlertType alertType;
    private String alertMessage;
    private LocalDateTime alertDate;
    @Enumerated(EnumType.STRING)
    private ResolvedStatus resolvedStatus;

/*    @Column(name = "health_metric_id")
    private Integer healthMetricId;

    @Column(name = "vital_signs_id")
    private Integer vitalSignsId;*/

}