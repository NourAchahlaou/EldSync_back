package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.EldSync.model.Event;

public interface IEventWishlistRepo extends JpaRepository<Event,Long> {
}
