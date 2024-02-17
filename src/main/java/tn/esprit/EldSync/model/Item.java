package tn.esprit.EldSync.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    private String name;
    private String description;
    private Integer quantity;
    private Integer unitPrice;
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private CategoryType category;

    enum CategoryType {
        MEDICATION,EQUIPMENT,SUPPLIES    }
    
}