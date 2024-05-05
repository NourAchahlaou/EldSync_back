package tn.esprit.EldSync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.EldSync.model.*;
import tn.esprit.EldSync.repositoy.HealthMetricRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HealthMetricService {

    private final HealthMetricRepository elderlyHealthMetricRepository;

    @Autowired
    public HealthMetricService(HealthMetricRepository elderlyHealthMetricRepository) {
        this.elderlyHealthMetricRepository = elderlyHealthMetricRepository;
    }

    public List<HealthMetric> getAllElderlyHealthMetrics() {
        return elderlyHealthMetricRepository.findAll();
    }

    public HealthMetric getElderlyHealthMetricById(int id) {
        return elderlyHealthMetricRepository.findById(id).orElse(null);
    }

    public HealthMetric addElderlyHealthMetric(HealthMetric elderlyHealthMetric) {
        createHealthAlertsForDangerousLevels(elderlyHealthMetric);
        // Additional validation or business logic can be added here before saving
        return elderlyHealthMetricRepository.save(elderlyHealthMetric);
    }

    public HealthMetric updateElderlyHealthMetric(int id, HealthMetric newElderlyHealthMetric) {
        Optional<HealthMetric> existingHealthMetricOptional = elderlyHealthMetricRepository.findById(id);
        if (existingHealthMetricOptional.isEmpty()) {
            throw new RuntimeException("HealthMetric not found with id: " + id);
        }
        // Check if the entity exists

        HealthMetric existingElderlyHealthMetric = existingHealthMetricOptional.get();
        // Update existing entity with new data
        existingElderlyHealthMetric.setObserverName(newElderlyHealthMetric.getObserverName());
        existingElderlyHealthMetric.setCholesterolLvl(newElderlyHealthMetric.getCholesterolLvl());
        existingElderlyHealthMetric.setBloodGlucoseLvl(newElderlyHealthMetric.getBloodGlucoseLvl());
        existingElderlyHealthMetric.setWeight(newElderlyHealthMetric.getWeight());
        existingElderlyHealthMetric.setHeight(newElderlyHealthMetric.getHeight());
        existingElderlyHealthMetric.setDate(newElderlyHealthMetric.getDate());
        createHealthAlertsForDangerousLevels(existingElderlyHealthMetric);
        return elderlyHealthMetricRepository.save(existingElderlyHealthMetric);
    }

    public void deleteElderlyHealthMetric(int id) {
        elderlyHealthMetricRepository.deleteById(id);
    }

    public void createHealthAlertsForDangerousLevels(HealthMetric healthMetric) {
        List<HealthAlerts> healthAlerts = new ArrayList<>();

        // Check each health metric attribute against predefined danger thresholds
        if (healthMetric.getCholesterolLvl() > 240) {
            healthAlerts.add(new HealthAlerts(AlertType.CHOLESTEROL_HIGH, "High cholesterol level detected", LocalDateTime.now(), ResolvedStatus.UNRESOLVED));
        }

        if (healthMetric.getBloodGlucoseLvl() > 140) {
            healthAlerts.add(new HealthAlerts(AlertType.BLOOD_GLUCOSE_HIGH, "High blood glucose level detected", LocalDateTime.now(), ResolvedStatus.UNRESOLVED));
        }

        // Get the height of the elder
        double height = healthMetric.getHeight();

        // Calculate the ideal weight range based on height (example thresholds, adjust as needed)
        double minHeight = 150; // Minimum height threshold (in centimeters)
        double maxHeight = 180; // Maximum height threshold (in centimeters)
        double minIdealWeight = minHeight * 0.45; // Minimum ideal weight (in kilograms)
        double maxIdealWeight = maxHeight * 0.45; // Maximum ideal weight (in kilograms)

        // Calculate the allowed weight range based on height
        double minAllowedWeight = minIdealWeight - 5; // Minimum allowed weight (in kilograms)
        double maxAllowedWeight = maxIdealWeight + 5; // Maximum allowed weight (in kilograms)

        // Check if the weight exceeds the allowed range
        double weight = healthMetric.getWeight();
        if (weight < minAllowedWeight || weight > maxAllowedWeight) {
            healthAlerts.add(new HealthAlerts(AlertType.WEIGHT_OUT_OF_RANGE, "Weight out of normal range for the given height", LocalDateTime.now(), ResolvedStatus.UNRESOLVED));
        }

        // Save the health alerts associated with the health metric
        healthMetric.setHealthAlerts(healthAlerts);
        elderlyHealthMetricRepository.save(healthMetric);
    }
}
