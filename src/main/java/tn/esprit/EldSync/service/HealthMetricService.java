package tn.esprit.EldSync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.EldSync.model.HealthMetric;
import tn.esprit.EldSync.repositoy.HealthMetricRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HealthMetricService {

    @Autowired
    private HealthMetricRepository healthMetricRepository;

    public List<HealthMetric> getAllHealthMetrics() {
        return healthMetricRepository.findAll();
    }

    public Optional<HealthMetric> getHealthMetricById(int id) {
        return healthMetricRepository.findById(id);
    }

    public HealthMetric saveOrUpdateHealthMetric(HealthMetric healthMetric) {
        return healthMetricRepository.save(healthMetric);
    }

    public void deleteHealthMetric(int id) {
        healthMetricRepository.deleteById(id);
    }
}
