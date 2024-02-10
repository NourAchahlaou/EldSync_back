package tn.esprit.EldSync.model.Inventory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "inventory_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyId;

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "issuanceId", referencedColumnName = "issuanceId")
    private InventoryIssuance issuance;

    @ManyToOne
    @JoinColumn(name = "removalId", referencedColumnName = "removalId")
    private InventoryRemoval removal;

    private String comment;
    private LocalDate historyDate;
}
