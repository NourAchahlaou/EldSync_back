package tn.esprit.EldSync.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.EldSync.model.Event;
import tn.esprit.EldSync.model.EventStatus;
import tn.esprit.EldSync.model.User;
import tn.esprit.EldSync.repositoy.IEventRepository;
import tn.esprit.EldSync.repositoy.UserRepo;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceEvent  {
    private final IEventRepository eventRepository;
    private final UserRepo userRepository;

    public List <Event> retrieveAllEvents(){return eventRepository.findAll();}

    public Event addEvent (Event event){return eventRepository.save(event);};
    
    public  void retrieveEvent(Long idEvent){ eventRepository.findById(idEvent);}
    
    public void removeEvent(Long idEvent){eventRepository.deleteById(idEvent);}
    
    public Event suggestNewEvent(Event event) {
        event.setStatus(EventStatus.PENDING);
        return eventRepository.save(event);
    }

    public Event getEventDetails(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }


    @Transactional

    public void registerUserForEvent(Integer idUser, Long eventId) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found"));

        user.getEvents().add(event);
        userRepository.save(user);
    }

    /*@Transactional

    public Set<Event> getRegisteredEventsForUser(Integer idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user.getEvents();
    }*/


    public User getUserWithMostEventsAttended() {
        return userRepository.findUserWithMostEvents();
    }


}




