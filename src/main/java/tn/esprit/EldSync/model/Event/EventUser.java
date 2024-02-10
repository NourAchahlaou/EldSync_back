package tn.esprit.EldSync.model.Event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "event_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventUserId;

    @ManyToOne
    @JoinColumn(name = "eventId", referencedColumnName = "eventId")
    private Event event;

    @Column(name = "userId") // Assume User ID is sourced from another service or database
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        registered, attended
    }
}