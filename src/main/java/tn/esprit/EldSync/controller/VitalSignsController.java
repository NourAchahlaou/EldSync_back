package tn.esprit.EldSync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.EldSync.model.VitalSigns;
import tn.esprit.EldSync.service.VitalSignsService;


import java.util.List;


@RestController
@RequestMapping("/vitalsigns")
public class VitalSignsController {
    private final VitalSignsService vitalSignsService;

    @Autowired
    public VitalSignsController(VitalSignsService vitalSignsService) {
        this.vitalSignsService = vitalSignsService;
    }

    @GetMapping
    public ResponseEntity<List<VitalSigns>> getAllVitalSigns() {
        List<VitalSigns> vitalSignsList = vitalSignsService.getAllVitalSigns();
        return new ResponseEntity<>(vitalSignsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalSigns> getVitalSignsById(@PathVariable int id) {
        VitalSigns vitalSigns = vitalSignsService.getVitalSignsById(id);
        if (vitalSigns != null) {
            return new ResponseEntity<>(vitalSigns, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<VitalSigns> addVitalSigns(@RequestBody VitalSigns vitalSigns) {
        VitalSigns createdVitalSigns = vitalSignsService.addVitalSigns(vitalSigns);
        return new ResponseEntity<>(createdVitalSigns, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VitalSigns> updateVitalSigns(@PathVariable int id, @RequestBody VitalSigns newVitalSigns) {
        VitalSigns updatedVitalSigns = vitalSignsService.updateVitalSigns(id, newVitalSigns);
        return new ResponseEntity<>(updatedVitalSigns, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVitalSigns(@PathVariable int id) {
        vitalSignsService.deleteVitalSigns(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
