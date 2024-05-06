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
import java.util.Date;
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

        // Check for dangerous levels and update health alerts if needed
        createHealthAlertsForDangerousLevelsVitals(newVitalSigns);

        // Save the updated vital signs
        return vitalSignsRepository.save(newVitalSigns);
    }


    // Delete vital signs by ID
    public void deleteVitalSigns(Integer id) {
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
    public VitalSigns getLatestAttributeUpdates() {
        List<VitalSigns> allVitalSigns = vitalSignsRepository.findAll();

        // Initialize variables to store the latest updates
        String latestNameOfObserver = "";
        Integer latestOxygenSaturation = null;
        Double latestTemperature = null;
        Integer latestHeartRate = null;
        Integer latestRespiratoryRate = null;
        String latestNameOfElder = "";
        Date latestMeasurementDate = null;

        // Iterate through all vital signs to find the latest updates
        for (VitalSigns vitalSigns : allVitalSigns) {
            if (vitalSigns.getNameOfObserver() != null) {
                latestNameOfObserver = vitalSigns.getNameOfObserver();
            }
            if (vitalSigns.getOxygenSaturation() != null) {
                latestOxygenSaturation = vitalSigns.getOxygenSaturation();
            }
            if (vitalSigns.getTemperature() != null) {
                latestTemperature = vitalSigns.getTemperature();
            }
            if (vitalSigns.getHeartRate() != null) {
                latestHeartRate = vitalSigns.getHeartRate();
            }
            if (vitalSigns.getRespiratoryRate() != null) {
                latestRespiratoryRate = vitalSigns.getRespiratoryRate();
            }
            if (vitalSigns.getNameOfElder() != null) {
                latestNameOfElder = vitalSigns.getNameOfElder();
            }
            if (vitalSigns.getDate() != null) {
                latestMeasurementDate = vitalSigns.getDate();
            }
        }

        // Create a new VitalSigns object with the latest updates
        VitalSigns latestUpdates = new VitalSigns();
        latestUpdates.setNameOfObserver(latestNameOfObserver);
        latestUpdates.setOxygenSaturation(latestOxygenSaturation);
        latestUpdates.setTemperature(latestTemperature);
        latestUpdates.setHeartRate(latestHeartRate);
        latestUpdates.setRespiratoryRate(latestRespiratoryRate);
        latestUpdates.setNameOfElder(latestNameOfElder);
        latestUpdates.setDate(latestMeasurementDate);

        return latestUpdates;
    }

}
