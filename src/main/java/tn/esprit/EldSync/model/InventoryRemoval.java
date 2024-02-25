package tn.esprit.EldSync.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory_removal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRemoval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer removalId;

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private Item item;

    private Integer quantityRemoved;
    private String reasonForRemoval;
    private Integer removedBy; // Assuming this is an ID
    private LocalDate removalDate;
}