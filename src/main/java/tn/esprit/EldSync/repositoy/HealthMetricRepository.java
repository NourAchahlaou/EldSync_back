package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.EldSync.model.HealthMetric;

@Repository
public interface HealthMetricRepository extends JpaRepository<HealthMetric, Integer> {
}