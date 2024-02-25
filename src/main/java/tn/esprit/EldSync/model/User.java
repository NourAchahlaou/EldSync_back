package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import tn.esprit.EldSync.model.Event;


import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity

public class User implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    private String email;
    private String password;
    private String gender;
    private Integer contactInfo;
    private String role;

    @OneToOne (cascade = CascadeType.ALL)
    private Profile profile;


    @ManyToMany(cascade = CascadeType.ALL )
    private Set<Event> events ;

}

