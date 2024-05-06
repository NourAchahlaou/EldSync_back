package tn.esprit.EldSync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.EldSync.model.HealthMetric;
import tn.esprit.EldSync.model.VitalSigns;
import tn.esprit.EldSync.service.HealthMetricService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/healthmetrics")
public class HealthMetricController {

    @Autowired
    private HealthMetricService healthMetricService;

    @GetMapping
    public List<HealthMetric> getAllHealthMetrics() {
        return healthMetricService.getAllElderlyHealthMetrics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthMetric> getHealthMetricById(@PathVariable("id") int id) {
        HealthMetric healthMetric = healthMetricService.getElderlyHealthMetricById(id);
        if (healthMetric != null) {
            return new ResponseEntity<>(healthMetric, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<HealthMetric> addHealthMetric(@RequestBody HealthMetric healthMetric) {
        HealthMetric newHealthMetric = healthMetricService.addElderlyHealthMetric(healthMetric);
        return new ResponseEntity<>(newHealthMetric, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthMetric> updateHealthMetric(@PathVariable("id") int id, @RequestBody HealthMetric healthMetric) {
        HealthMetric updatedHealthMetric = healthMetricService.updateElderlyHealthMetric(id, healthMetric);
        return new ResponseEntity<>(updatedHealthMetric, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthMetric(@PathVariable("id") int id) {
        healthMetricService.deleteElderlyHealthMetric(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/health-alerts")
    public void createHealthAlertsForDangerousLevels(@RequestBody HealthMetric healthMetric) {
        healthMetricService.createHealthAlertsForDangerousLevels(healthMetric);
    }

    @GetMapping("/latest-updates")
    public ResponseEntity<HealthMetric> getLastUpdatesForAttributes() {
        HealthMetric latestUpdates = healthMetricService.getLastUpdatesForAttributes();
        if (latestUpdates != null) {
            return new ResponseEntity<>(latestUpdates, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
