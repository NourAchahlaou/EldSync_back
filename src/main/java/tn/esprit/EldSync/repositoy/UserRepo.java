package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.EldSync.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {



}
