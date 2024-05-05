package tn.esprit.EldSync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.EldSync.model.HealthMetric;
import tn.esprit.EldSync.service.HealthMetricService;


import java.util.List;


@RestController
@RequestMapping("/vitalsigns")
public class VitalSignsController {

    private final HealthMetricService elderlyHealthMetricService;

    @Autowired
    public VitalSignsController(HealthMetricService elderlyHealthMetricService) {
        this.elderlyHealthMetricService = elderlyHealthMetricService;
    }

    @GetMapping
    public ResponseEntity<List<HealthMetric>> getAllElderlyHealthMetrics() {
        List<HealthMetric> elderlyHealthMetrics = elderlyHealthMetricService.getAllElderlyHealthMetrics();
        return ResponseEntity.ok(elderlyHealthMetrics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthMetric> getElderlyHealthMetricById(@PathVariable int id) {
        HealthMetric elderlyHealthMetric = elderlyHealthMetricService.getElderlyHealthMetricById(id);
        return ResponseEntity.ok(elderlyHealthMetric);
    }

    @PostMapping
    public ResponseEntity<HealthMetric> addElderlyHealthMetric(@RequestBody HealthMetric elderlyHealthMetric) {
        HealthMetric createdElderlyHealthMetric = elderlyHealthMetricService.addElderlyHealthMetric(elderlyHealthMetric);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdElderlyHealthMetric);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthMetric> updateElderlyHealthMetric(@PathVariable int id, @RequestBody HealthMetric elderlyHealthMetric) {
        HealthMetric updatedElderlyHealthMetric = elderlyHealthMetricService.updateElderlyHealthMetric(id, elderlyHealthMetric);
        return ResponseEntity.ok(updatedElderlyHealthMetric);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElderlyHealthMetric(@PathVariable int id) {
        elderlyHealthMetricService.deleteElderlyHealthMetric(id);
        return ResponseEntity.noContent().build();
    }
}
