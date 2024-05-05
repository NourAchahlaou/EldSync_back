package tn.esprit.EldSync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.EldSync.model.HealthMetric;
import tn.esprit.EldSync.repositoy.HealthMetricRepository;

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
        // Additional validation or business logic can be added here before saving
        return elderlyHealthMetricRepository.save(elderlyHealthMetric);
    }

    public HealthMetric updateElderlyHealthMetric(int id, HealthMetric newElderlyHealthMetric) {
        // Check if the entity exists
        HealthMetric existingElderlyHealthMetric = elderlyHealthMetricRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ElderlyHealthMetric not found with id: " + id));

        // Update existing entity with new data
        existingElderlyHealthMetric.setObserverName(newElderlyHealthMetric.getObserverName());
        existingElderlyHealthMetric.setCholesterolLvl(newElderlyHealthMetric.getCholesterolLvl());
        existingElderlyHealthMetric.setBloodGlucoseLvl(newElderlyHealthMetric.getBloodGlucoseLvl());
        existingElderlyHealthMetric.setWeight(newElderlyHealthMetric.getWeight());
        existingElderlyHealthMetric.setHeight(newElderlyHealthMetric.getHeight());
        existingElderlyHealthMetric.setDate(newElderlyHealthMetric.getDate());

        return elderlyHealthMetricRepository.save(existingElderlyHealthMetric);
    }

    public void deleteElderlyHealthMetric(int id) {
        elderlyHealthMetricRepository.deleteById(id);
    }
}
