package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "event_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistoryItem;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Enumerated(EnumType.STRING)
    private Attendance attendance;

    private Date createdAt;



enum Attendance {
    MISSED, ATTENDED
}
}