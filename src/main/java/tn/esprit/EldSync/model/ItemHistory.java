package tn.esprit.EldSync.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory_history")
@Data
@NoArgsConstructor
@AllArgsConstructor

    public class ItemHistory {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Integer historyId;
        private String action;
        private LocalDateTime timestamp;
        private String details;
        private Long itemId;




}
