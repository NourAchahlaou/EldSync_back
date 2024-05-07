package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class LifeJourneyStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date StartWeek;
    private Date EndWeek;
    @Enumerated(EnumType.STRING)
    @Column(name = "zone_prediction")
    private ZonePrediction zonePrediction;



}
