package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idTask;
    LocalDate date;
    Time time;

    @Enumerated(EnumType.STRING)
    Importance importance;


    @Enumerated(EnumType.STRING)
    StatusTask status;

}
