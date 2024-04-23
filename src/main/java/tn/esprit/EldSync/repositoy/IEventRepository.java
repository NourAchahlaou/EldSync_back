package tn.esprit.EldSync.repositoy;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.EldSync.model.Event;

public interface IEventRepository  extends JpaRepository<Event,Long> {
    List<Event> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Event> findByDateAfter(Date currentDate);

    List<Event> findByDateBefore(LocalDate date);
    List<Event> findByCategory(String category);


}
