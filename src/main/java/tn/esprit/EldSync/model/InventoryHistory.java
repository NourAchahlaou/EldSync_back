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
