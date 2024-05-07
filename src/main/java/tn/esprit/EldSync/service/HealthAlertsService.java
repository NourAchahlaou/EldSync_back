package tn.esprit.EldSync.service;

import org.springframework.stereotype.Service;
import tn.esprit.EldSync.model.HealthAlerts;
import tn.esprit.EldSync.repositoy.HealthAlertRepository;
import tn.esprit.EldSync.repositoy.HealthMetricRepository;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HealthAlertsService {
    private final HealthAlertRepository healthAlertRepository;
    public HealthAlertsService(HealthAlertRepository healthAlertRepository) {
        this.healthAlertRepository = healthAlertRepository;
    }

    public List<HealthAlerts> getAllHealthMetrics() {
        return healthAlertRepository.findAll();
    }
    public long getAlertsCount() {
        return healthAlertRepository.count();
    }

    public Map<YearMonth, Long> getMonthlyAlertsCount() {
        List<Object[]> results = healthAlertRepository.getMonthlyAlertsCount();

        // Convert the result directly to a map of YearMonth to count
        return results.stream()
                .collect(Collectors.toMap(
                        // Assuming the first two columns are the year and month
                        // Adjust this according to your actual query result
                        data -> YearMonth.of((Integer) data[0], (Integer) data[1]),
                        data -> (Long) data[2]
                ));
    }
}
