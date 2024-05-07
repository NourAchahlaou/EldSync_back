package tn.esprit.EldSync.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.EldSync.model.LifeJourneyStats;
import tn.esprit.EldSync.model.ZonePrediction;
import tn.esprit.EldSync.service.LifeJourneyStatsService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/all-life-journey-stats")
public class LifeJourneyStatsController {

    private final LifeJourneyStatsService lifeJourneyStatsService;

    public LifeJourneyStatsController(LifeJourneyStatsService lifeJourneyStatsService) {
        this.lifeJourneyStatsService = lifeJourneyStatsService;
    }

    @GetMapping
    public List<LifeJourneyStats> getAllLifeJourneyStats() {
        return lifeJourneyStatsService.getAllLifeJourneyStats();
    }
    @GetMapping("/stats/count-by-zone")
    public Map<ZonePrediction, Integer> getLifeJourneyStatsCountByZone() {
        return lifeJourneyStatsService.calculateLifeJourneyStatsCountByZone();
    }
}
