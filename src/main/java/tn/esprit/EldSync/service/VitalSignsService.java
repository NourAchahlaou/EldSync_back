package tn.esprit.EldSync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.EldSync.model.Profile;
import tn.esprit.EldSync.model.VitalSigns;
import tn.esprit.EldSync.repositoy.ProfileRepository;
import tn.esprit.EldSync.repositoy.VitalSignsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VitalSignsService {

    @Autowired
    private VitalSignsRepository vitalSignsRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<VitalSigns> getAllVitalSigns() {
        return vitalSignsRepository.findAll();
    }

    public Optional<VitalSigns> getVitalSignsById(int id) {
        return vitalSignsRepository.findById(id);
    }

    public VitalSigns saveOrUpdateVitalSigns(VitalSigns vitalSigns) {
        // Ensure that elder and measurementTaker profiles are not null
        if (vitalSigns.getElder() == null || vitalSigns.getMeasurementTaker() == null) {
            throw new IllegalArgumentException("Elder and MeasurementTaker profiles must be provided.");
        }

        // Retrieve or save elder profile
        Profile elder = profileRepository.findById(vitalSigns.getElder().getIdProfile())
                .orElse(profileRepository.save(vitalSigns.getElder()));
        vitalSigns.setElder(elder);

        // Retrieve or save measurementTaker profile
        Profile measurementTaker = profileRepository.findById(vitalSigns.getMeasurementTaker().getIdProfile())
                .orElse(profileRepository.save(vitalSigns.getMeasurementTaker()));
        vitalSigns.setMeasurementTaker(measurementTaker);

        // Save or update VitalSigns entity
        return vitalSignsRepository.save(vitalSigns);
    }

    public void deleteVitalSigns(int id) {
        vitalSignsRepository.deleteById(id);
    }
}
