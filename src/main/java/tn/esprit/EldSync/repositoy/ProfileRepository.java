package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.EldSync.model.Profile;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}