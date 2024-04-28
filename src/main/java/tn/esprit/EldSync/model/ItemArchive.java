package tn.esprit.EldSync.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "itemsArchive")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idarchive")

    private Long idarchive;
    private String name;
    private String description;
    private Integer quantity;
    private Integer unitPrice;
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private ItemCategory category;
}
