package tn.esprit.EldSync.model.Inventory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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