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
