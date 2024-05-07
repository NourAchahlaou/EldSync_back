package tn.esprit.EldSync.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.EldSync.model.HealthAlerts;
import tn.esprit.EldSync.service.HealthAlertsService;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/All-health-alerts")
public class HealthAlertsController {
    private final HealthAlertsService healthAlertsService;
    public HealthAlertsController(HealthAlertsService healthAlertsService) {
        this.healthAlertsService = healthAlertsService;
    }

    @GetMapping
    public List<HealthAlerts> getAllHealthMetrics() {
        return healthAlertsService.getAllHealthMetrics();
    }

    @GetMapping("/alerts/count")
    public long getAlertsCount() {
        return healthAlertsService.getAlertsCount();
    }

    @GetMapping("/monthly-count")
    public Map<YearMonth, Long> getMonthlyAlertsCount() {
        return healthAlertsService.getMonthlyAlertsCount();
    }
}
