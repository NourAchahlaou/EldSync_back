package tn.esprit.EldSync.model.Event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "event_suggestions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventSugId;

    private String name;

    private String imageCover;

    @Lob
    private String description;

    private LocalDate date;

    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private Category category;

    public enum Category {
        Entertainment, OutdoorActivities, HealthCheckup, SocialGathering
    }
}