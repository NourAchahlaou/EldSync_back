package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.EldSync.model.VitalSigns;

import java.util.Date;
import java.util.List;

@Repository
public interface VitalSignsRepository extends JpaRepository<VitalSigns, Integer> {
    @Query("SELECT MIN(vs.date) FROM VitalSigns vs")
    Date findEarliestMeasurementDate();

    @Query("SELECT MAX(vs.date) FROM VitalSigns vs")
    Date findLatestMeasurementDate();

    List<VitalSigns> findByDateBetween(Date startDate, Date endDate);
}
