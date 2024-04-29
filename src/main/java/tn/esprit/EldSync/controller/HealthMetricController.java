package tn.esprit.EldSync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.EldSync.model.HealthMetric;
import tn.esprit.EldSync.service.HealthMetricService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/healthmetrics")
public class HealthMetricController {

    @Autowired
    private HealthMetricService healthMetricService;

    @GetMapping
    public List<HealthMetric> getAllHealthMetrics() {
        return healthMetricService.getAllHealthMetrics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthMetric> getHealthMetricById(@PathVariable("id") int id) {
        Optional<HealthMetric> healthMetric = healthMetricService.getHealthMetricById(id);
        return healthMetric.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<HealthMetric> addHealthMetric(@RequestBody HealthMetric healthMetric) {
        HealthMetric newHealthMetric = healthMetricService.saveOrUpdateHealthMetric(healthMetric);
        return new ResponseEntity<>(newHealthMetric, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthMetric> updateHealthMetric(@PathVariable("id") int id, @RequestBody HealthMetric healthMetric) {
        healthMetric.setId(id);
        HealthMetric updatedHealthMetric = healthMetricService.saveOrUpdateHealthMetric(healthMetric);
        return new ResponseEntity<>(updatedHealthMetric, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthMetric(@PathVariable("id") int id) {
        healthMetricService.deleteHealthMetric(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
