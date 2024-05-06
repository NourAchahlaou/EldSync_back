package tn.esprit.EldSync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.EldSync.model.*;
import tn.esprit.EldSync.repositoy.HealthMetricRepository;

import java.time.LocalDateTime;
import java.util.*;

import static java.sql.Types.NULL;

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
    public Map<String, Object> getLastUpdatesForAttributes() {
        List<HealthMetric> healthMetrics = getAllElderlyHealthMetrics();
        Map<String, Object> lastUpdates = new HashMap<>();

        // Initialize variables to store the latest values
        HealthMetric latestCholesterolMetric = null;
        HealthMetric latestBloodGlucoseMetric = null;
        HealthMetric latestWeightMetric = null;

        // Loop through each health metric to find the latest updates
        for (HealthMetric healthMetric : healthMetrics) {
            // Check and update cholesterol level
            if (healthMetric.getCholesterolLvl() != null &&
                    (latestCholesterolMetric == null || healthMetric.getDate().after(latestCholesterolMetric.getDate()))) {
                latestCholesterolMetric = healthMetric;
            }

            // Check and update blood glucose level
            if (healthMetric.getBloodGlucoseLvl() != null &&
                    (latestBloodGlucoseMetric == null || healthMetric.getDate().after(latestBloodGlucoseMetric.getDate()))) {
                latestBloodGlucoseMetric = healthMetric;
            }

            // Check and update weight
            if (healthMetric.getWeight() != null &&
                    (latestWeightMetric == null || healthMetric.getDate().after(latestWeightMetric.getDate()))) {
                latestWeightMetric = healthMetric;
            }
        }

        // Add the latest updates to the map
        lastUpdates.put("cholesterolLvl", latestCholesterolMetric != null ? latestCholesterolMetric.getCholesterolLvl() : null);
        lastUpdates.put("bloodGlucoseLvl", latestBloodGlucoseMetric != null ? latestBloodGlucoseMetric.getBloodGlucoseLvl() : null);
        lastUpdates.put("weight", latestWeightMetric != null ? latestWeightMetric.getWeight() : null);

        return lastUpdates;
    }
}
