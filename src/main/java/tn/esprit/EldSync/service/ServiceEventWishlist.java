package tn.esprit.EldSync.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.EldSync.model.Event;
import tn.esprit.EldSync.repositoy.IEventWishlistRepo;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceEventWishlist {


    private final IEventWishlistRepo eventWishlistRepo;

    public Event addToWishlist(Long eventId) {
        Optional<Event> optionalEvent = eventWishlistRepo.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            return event;
        }
        return null;
    }
}
