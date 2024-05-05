package tn.esprit.EldSync.repositoy;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import tn.esprit.EldSync.model.Event;
import tn.esprit.EldSync.Entity.User;

public interface IEventRepository  extends JpaRepository<Event,Long> {
    List<Event> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Event> findByDateAfter(Date currentDate);

    List<Event> findByDateBefore(LocalDate date);
    List<Event> findByCategory(String category);


    @Query("SELECT e FROM Event e JOIN e.attendees a GROUP BY e.idEvent ORDER BY COUNT(a) DESC")
    List<Event> findEventsByPopularity(Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.idEvent NOT IN (SELECT eu.idEvent FROM User u JOIN u.events eu WHERE u.id = :userId) ORDER BY (SELECT COUNT(ue) FROM Event ue JOIN ue.attendees a WHERE ue.idEvent = e.idEvent) DESC")
    List<Event> findPopularEventsUserNotRegisteredTo(@Param("userId") Long userId, Pageable pageable);

}


