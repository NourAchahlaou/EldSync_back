package tn.esprit.EldSync.Repo;


import tn.esprit.EldSync.Entity.Role;
import tn.esprit.EldSync.Enum.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,String> {
    Optional<Role> findByName (ERole name);
    boolean existsByName(ERole r1);
    //Optional<Role> findByName ();

}
