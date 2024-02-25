package tn.esprit.EldSync.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfile;
    private String firstName;
    private String lastName;
    private Byte photo;
    private String Descrpiton;
    private Integer contactInfo;

    @OneToOne (mappedBy = "profile")
    private User user;

}
