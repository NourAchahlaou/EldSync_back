package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String name;

    @Lob // Large Object for handling large data fields
    private String description;

    private String image;

    private LocalDate date;

    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String location;

    private Double price;

    private String venue;

    public enum Category {
        Entertainment, OutdoorActivities, HealthCheckup, SocialGathering
    }
}