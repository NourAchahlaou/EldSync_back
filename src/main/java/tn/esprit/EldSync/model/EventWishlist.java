package tn.esprit.EldSync.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event_wishlists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventWishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idWishlistItem;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private Date addedAt;

}