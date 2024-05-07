package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.EldSync.model.LifeJourneyStats;

@Repository
public interface LifeJourneyStatsRepository extends JpaRepository<LifeJourneyStats, Integer> {
}
