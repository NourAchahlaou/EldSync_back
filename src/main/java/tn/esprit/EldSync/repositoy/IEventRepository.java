package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.EldSync.model.Event;

import java.util.Date;
import java.util.List;

public interface IEventRepository  extends JpaRepository<Event,Long> {
    List<Event> findByDateBetween(Date fromDate, Date toDate);
    List<Event> findByDateAfter(Date currentDate);

    List<Event> findByDateBefore(Date currentDate);
    List<Event> findByCategory(String category);


}
