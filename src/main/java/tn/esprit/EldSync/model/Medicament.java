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
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idMed ;

    String name;
    Float dosage;
    LocalDate prescriptionDate;
    LocalDate renewalDate;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<MedicationReminder> medicationReminders;

}
