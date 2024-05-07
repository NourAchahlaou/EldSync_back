package tn.esprit.EldSync.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tn.esprit.EldSync.model.HealthAlerts;
import tn.esprit.EldSync.service.HealthAlertsService;

import java.util.List;

@Controller
@RequestMapping("/health-alerts")
public class HealthAlertsController {
    private final HealthAlertsService healthAlertsService;
    public HealthAlertsController(HealthAlertsService healthAlertsService) {
        this.healthAlertsService = healthAlertsService;
    }

    @GetMapping
    public List<HealthAlerts> getAllHealthMetrics() {
        return healthAlertsService.getAllHealthMetrics();
    }
}
