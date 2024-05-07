package tn.esprit.EldSync.service;

import org.springframework.stereotype.Service;
import tn.esprit.EldSync.model.LifeJourneyStats;
import tn.esprit.EldSync.repositoy.LifeJourneyStatsRepository;

import java.util.List;

@Service
public class LifeJourneyStatsService {

    private final LifeJourneyStatsRepository lifeJourneyStatsRepository;

    public LifeJourneyStatsService(LifeJourneyStatsRepository lifeJourneyStatsRepository) {
        this.lifeJourneyStatsRepository = lifeJourneyStatsRepository;
    }

    public List<LifeJourneyStats> getAllLifeJourneyStats() {
        return lifeJourneyStatsRepository.findAll();
    }
}
