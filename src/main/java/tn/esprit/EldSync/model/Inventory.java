package tn.esprit.EldSync.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private Item item;

    private Integer quantity;
    private Integer unitPrice;
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    enum Status {
        MEDICATION,EQUIPMENT,SUPPLIES    }

}