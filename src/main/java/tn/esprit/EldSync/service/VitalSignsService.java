package tn.esprit.EldSync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.EldSync.model.AlertType;
import tn.esprit.EldSync.model.HealthAlerts;
import tn.esprit.EldSync.model.ResolvedStatus;
import tn.esprit.EldSync.model.VitalSigns;
import tn.esprit.EldSync.repositoy.VitalSignsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VitalSignsService {
    private final VitalSignsRepository vitalSignsRepository;



    @Autowired
    public VitalSignsService(VitalSignsRepository vitalSignsRepository) {
        this.vitalSignsRepository = vitalSignsRepository;
    }

    // Get all vital signs
    public List<VitalSigns> getAllVitalSigns() {
        return vitalSignsRepository.findAll();
    }

    // Get vital signs by ID
    public VitalSigns getVitalSignsById(int id) {
        Optional<VitalSigns> vitalSigns = vitalSignsRepository.findById(id);
        return vitalSigns.orElse(null);
    }

    // Add new vital signs
// Add new vital signs
    public VitalSigns addVitalSigns(VitalSigns vitalSigns) {
        // Check for dangerous levels and create health alerts if needed
        createHealthAlertsForDangerousLevelsVitals(vitalSigns);

        // Additional validation or business logic can be added here before saving
        return vitalSignsRepository.save(vitalSigns);
    }


    // Update vital signs
    public VitalSigns updateVitalSigns(int id, VitalSigns newVitalSigns) {
        Optional<VitalSigns> existingVitalSignsOptional = vitalSignsRepository.findById(id);
        if (existingVitalSignsOptional.isEmpty()) {
            throw new RuntimeException("VitalSigns not found with id: " + id);
        }

        VitalSigns existingVitalSigns = existingVitalSignsOptional.get();
        // Update existing vital signs with new data
        existingVitalSigns.setNameOfObserver(newVitalSigns.getNameOfObserver());
        existingVitalSigns.setOxygenSaturation(newVitalSigns.getOxygenSaturation());
        existingVitalSigns.setTemperature(newVitalSigns.getTemperature());
        existingVitalSigns.setHeartRate(newVitalSigns.getHeartRate());
        existingVitalSigns.setRespiratoryRate(newVitalSigns.getRespiratoryRate());
        existingVitalSigns.setNameOfElder(newVitalSigns.getNameOfElder());
        existingVitalSigns.setDate(newVitalSigns.getDate());

        // Check for dangerous levels and create health alerts if needed
        createHealthAlertsForDangerousLevelsVitals(existingVitalSigns);

        return vitalSignsRepository.save(existingVitalSigns);
    }

    // Delete vital signs by ID
    public void deleteVitalSigns(int id) {
        vitalSignsRepository.deleteById(id);
    }

    // Check vital signs for dangerous levels and create health alerts
// Check vital signs for dangerous levels and create health alerts
    public void createHealthAlertsForDangerousLevelsVitals(VitalSigns vitalSigns) {
        List<HealthAlerts> healthAlerts = new ArrayList<>();

        // Check each vital sign attribute against predefined danger thresholds
        if (vitalSigns.getOxygenSaturation() < 90) {
            healthAlerts.add(new HealthAlerts(AlertType.OXYGEN_SATURATION_LOW, "Low oxygen saturation detected", LocalDateTime.now(), ResolvedStatus.UNRESOLVED));
        }

        if (vitalSigns.getTemperature() > 38) {
            healthAlerts.add(new HealthAlerts(AlertType.TEMPERATURE_HIGH, "High temperature detected", LocalDateTime.now(), ResolvedStatus.UNRESOLVED));
        }

        if (vitalSigns.getHeartRate() > 100 || vitalSigns.getHeartRate() < 60) {
            healthAlerts.add(new HealthAlerts(AlertType.HEART_RATE_OUT_OF_RANGE, "Heart rate out of normal range", LocalDateTime.now(), ResolvedStatus.UNRESOLVED));
        }

        if (vitalSigns.getRespiratoryRate() > 20 || vitalSigns.getRespiratoryRate() < 12) {
            healthAlerts.add(new HealthAlerts(AlertType.RESPIRATORY_RATE_OUT_OF_RANGE, "Respiratory rate out of normal range", LocalDateTime.now(), ResolvedStatus.UNRESOLVED));
        }

        // Set the health alerts associated with the vital signs
        vitalSigns.setHealthAlerts(healthAlerts);
    }

}
