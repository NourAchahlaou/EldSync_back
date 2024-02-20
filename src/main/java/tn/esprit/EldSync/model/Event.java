package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvent;

    private String name;

    private String description;

    private String banner;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.STRING)
    private EventCategory category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address location;

    private float price;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // Constructors, Getters, and Setters
}

enum EventCategory {
    ENTERTAINMENT, OUTDOOR_ACTIVITIES, HEALTH_CHECKUP, SOCIAL_GATHERING
}

enum EventStatus {
    APPROVED, PENDING
}