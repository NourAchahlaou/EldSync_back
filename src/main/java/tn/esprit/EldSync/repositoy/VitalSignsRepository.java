package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.EldSync.model.VitalSigns;

@Repository
public interface VitalSignsRepository extends JpaRepository<VitalSigns, Integer> {
}