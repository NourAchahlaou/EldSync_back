package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.EldSync.model.HealthAlerts;

@Repository
public interface HealthAlertRepository extends JpaRepository<HealthAlerts, Integer> {
}
