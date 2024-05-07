package tn.esprit.EldSync.service;

import org.springframework.stereotype.Service;
import tn.esprit.EldSync.model.HealthAlerts;
import tn.esprit.EldSync.repositoy.HealthAlertRepository;
import tn.esprit.EldSync.repositoy.HealthMetricRepository;

import java.util.List;

@Service
public class HealthAlertsService {
    private final HealthAlertRepository healthAlertRepository;
    public HealthAlertsService(HealthAlertRepository healthAlertRepository) {
        this.healthAlertRepository = healthAlertRepository;
    }

    public List<HealthAlerts> getAllHealthMetrics() {
        return healthAlertRepository.findAll();
    }
}
