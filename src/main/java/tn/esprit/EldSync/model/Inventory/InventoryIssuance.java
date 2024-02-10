package tn.esprit.EldSync.model.Inventory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "inventory_issuance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryIssuance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer issuanceId;

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private Item item;

    private Integer quantity;
    private String reasonForIssuance;
    private Integer issuedBy; // Assuming this is an ID referencing a User entity
    private Integer issuedTo; // Assuming this is an ID referencing an Elder entity
    private LocalDate issuanceDate;
}
