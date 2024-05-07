package tn.esprit.EldSync.service;

import org.springframework.stereotype.Service;
import tn.esprit.EldSync.model.LifeJourneyStats;
import tn.esprit.EldSync.model.ZonePrediction;
import tn.esprit.EldSync.repositoy.LifeJourneyStatsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LifeJourneyStatsService {

    private final LifeJourneyStatsRepository lifeJourneyStatsRepository;

    public LifeJourneyStatsService(LifeJourneyStatsRepository lifeJourneyStatsRepository) {
        this.lifeJourneyStatsRepository = lifeJourneyStatsRepository;
    }

    public List<LifeJourneyStats> getAllLifeJourneyStats() {
        return lifeJourneyStatsRepository.findAll();
    }
    public Map<ZonePrediction, Integer> calculateLifeJourneyStatsCountByZone() {
        List<LifeJourneyStats> allLifeJourneyStats = lifeJourneyStatsRepository.findAll();
        Map<tn.esprit.EldSync.model.ZonePrediction, Integer> zoneCountMap = new HashMap<>();
        for (tn.esprit.EldSync.model.ZonePrediction zonePrediction : tn.esprit.EldSync.model.ZonePrediction.values()) {
            long count = allLifeJourneyStats.stream()
                    .filter(stats -> stats.getZonePrediction() == zonePrediction)
                    .count();
            zoneCountMap.put(zonePrediction, (int) count); // Cast count to int
        }
        return zoneCountMap;
    }
}
