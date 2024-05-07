package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.EldSync.model.HealthAlerts;

import java.util.List;

@Repository
public interface HealthAlertRepository extends JpaRepository<HealthAlerts, Integer> {
    @Query("SELECT YEAR(alertDate), MONTH(alertDate), COUNT(*) " +
            "FROM HealthAlerts " +
            "GROUP BY YEAR(alertDate), MONTH(alertDate)")
    List<Object[]> getMonthlyAlertsCount();
}
