package tn.esprit.EldSync.model;

import jakarta.persistence.*;
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
public class User implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    private String email;
    private String password;
    private String gender;
    private Integer contacInfo;
    private String role;
    @OneToOne
    private Profile profile;
}

