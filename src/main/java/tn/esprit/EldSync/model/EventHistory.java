package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "event_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventHistoryId;

    @ManyToOne
    @JoinColumn(name = "eventId", referencedColumnName = "eventId")
    private Event event;

    @Enumerated(EnumType.STRING)
    private Type type;

    private LocalDate date;

    public enum Type {
        upcoming, past, Rescheduled
    }
}