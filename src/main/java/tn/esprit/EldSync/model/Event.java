package tn.esprit.EldSync.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import tn.esprit.EldSync.Entity.User;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvent;

    private String name;

    private String description;

    private String banner;

    private Date date;

    @Enumerated(EnumType.STRING)
    private EventCategory category;
    

    private String location;

    private float price;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    private Date createdAt;

    private Date updatedAt;
    @JsonIgnore

    @ManyToMany (mappedBy ="events",cascade = CascadeType.ALL)
    private Set<User> users;

}

