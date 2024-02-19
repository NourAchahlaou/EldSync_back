package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Services implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idService;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    @ManyToMany (mappedBy = "services")
    private Set<Subscription>subscriptions;
}
