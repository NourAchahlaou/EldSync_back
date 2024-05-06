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
@Table(name = "health_Alerts")
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
    public HealthAlerts(AlertType alertType, String alertMessage, LocalDateTime alertDate, ResolvedStatus resolvedStatus) {
        this.alertType = alertType;
        this.alertMessage = alertMessage;
        this.alertDate = alertDate;
        this.resolvedStatus = resolvedStatus;
    }

}
