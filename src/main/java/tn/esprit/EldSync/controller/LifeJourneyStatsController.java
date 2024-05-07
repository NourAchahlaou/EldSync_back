package tn.esprit.EldSync.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tn.esprit.EldSync.model.LifeJourneyStats;
import tn.esprit.EldSync.service.LifeJourneyStatsService;

import java.util.List;

@Controller
@RequestMapping("/life-journey-stats")
public class LifeJourneyStatsController {

    private final LifeJourneyStatsService lifeJourneyStatsService;

    public LifeJourneyStatsController(LifeJourneyStatsService lifeJourneyStatsService) {
        this.lifeJourneyStatsService = lifeJourneyStatsService;
    }

    @GetMapping
    public List<LifeJourneyStats> getAllLifeJourneyStats() {
        return lifeJourneyStatsService.getAllLifeJourneyStats();
    }
}
