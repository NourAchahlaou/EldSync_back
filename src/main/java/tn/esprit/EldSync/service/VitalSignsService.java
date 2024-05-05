package tn.esprit.EldSync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.EldSync.model.VitalSigns;
import tn.esprit.EldSync.repositoy.VitalSignsRepository;

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
    public VitalSigns addVitalSigns(VitalSigns vitalSigns) {
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

        return vitalSignsRepository.save(existingVitalSigns);
    }

    // Delete vital signs by ID
    public void deleteVitalSigns(int id) {
        vitalSignsRepository.deleteById(id);
    }
}
