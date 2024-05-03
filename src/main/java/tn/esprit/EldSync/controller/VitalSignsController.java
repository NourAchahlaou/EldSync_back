package tn.esprit.EldSync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.EldSync.model.VitalSigns;
import tn.esprit.EldSync.service.VitalSignsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vitalsigns")
public class VitalSignsController {

    @Autowired
    private VitalSignsService vitalSignsService;

    @GetMapping
    public List<VitalSigns> getAllVitalSigns() {
        return vitalSignsService.getAllVitalSigns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalSigns> getVitalSignsById(@PathVariable("id") int id) {
        Optional<VitalSigns> vitalSigns = vitalSignsService.getVitalSignsById(id);
        return vitalSigns.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<VitalSigns> addVitalSigns(@RequestBody VitalSigns vitalSigns) {
        VitalSigns newVitalSigns = vitalSignsService.saveOrUpdateVitalSigns(vitalSigns);
        return new ResponseEntity<>(newVitalSigns, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VitalSigns> updateVitalSigns(@PathVariable("id") int id, @RequestBody VitalSigns vitalSigns) {
        vitalSigns.setId(id);
        VitalSigns updatedVitalSigns = vitalSignsService.saveOrUpdateVitalSigns(vitalSigns);
        return new ResponseEntity<>(updatedVitalSigns, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVitalSigns(@PathVariable("id") int id) {
        vitalSignsService.deleteVitalSigns(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
