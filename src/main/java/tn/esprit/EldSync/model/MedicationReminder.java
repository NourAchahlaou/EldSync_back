package tn.esprit.EldSync.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicationReminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idReminder;
    LocalDate date;
    LocalDate time;

    @Enumerated(EnumType.STRING)
    ReminderStatus reminderStatus;


    @ManyToMany (mappedBy = "medicationReminders")
    private Set<Medicament> medicaments;
}
