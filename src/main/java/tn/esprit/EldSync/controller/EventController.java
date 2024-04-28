package tn.esprit.EldSync.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.EldSync.model.Event;
import tn.esprit.EldSync.Entity.User;
import tn.esprit.EldSync.service.ServiceEvent;


import java.util.Date;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("EldSync/Event")
@RequiredArgsConstructor
public class EventController {

    private final ServiceEvent serviceEvent;



    @PostMapping("/AddEvent")
    public Event AddEvent(@RequestBody Event event){return serviceEvent.addEvent(event);}

    @PutMapping("/UpdateEvent")
    public Event UpdateEvent(@RequestBody Event event){return serviceEvent.addEvent(event);}

    @GetMapping("/retrieveAllEvents")
    public List<Event> retrieveAllEvents(){return serviceEvent.retrieveAllEvents();}

    @GetMapping("/retrieveEventById/{idEvent}")
    public  void retrieveEvent(@PathVariable("idEvent") Long idEvent){serviceEvent.retrieveEvent(idEvent);}

    @DeleteMapping("/removeEvent/{idEvent}")
    public void removeEvent(@PathVariable("idEvent") Long idEvent){serviceEvent.removeEvent(idEvent);}

    @PostMapping("/suggestNewEvent")
    public Event suggestNewEvent(@RequestBody Event event) {
        return serviceEvent.suggestNewEvent(event);
    }

    @GetMapping("/getEventDetails/{idEvent}")
    public Event getEventDetails(@PathVariable("idEvent") Long idEvent) {
        return serviceEvent.getEventDetails(idEvent);
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUserForEvent(@RequestParam Integer idUser, @RequestParam Long eventId) {
        serviceEvent.registerUserForEvent(idUser, eventId);
        return ResponseEntity.ok("User registered for the event");
    }


    @GetMapping("/user-with-most-events")

    public ResponseEntity<User> getUserWithMostEventsAttended() {
        User userWithMostEvents = serviceEvent.getUserWithMostEventsAttended();

        if (userWithMostEvents != null) {
            return ResponseEntity.ok(userWithMostEvents);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/by-donation-date")
    public ResponseEntity<List<Event>> getEventsByDonationDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {

        List<Event> events = serviceEvent.getEventsByDonationDateRange(fromDate, toDate);

        if (!events.isEmpty()) {
            return ResponseEntity.ok(events);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/upcomingEvent")
    public ResponseEntity<List<Event>> getUpcomingEvents() {
        List<Event> upcomingEvents = serviceEvent.getUpcomingEvents();

        if (!upcomingEvents.isEmpty()) {
            return ResponseEntity.ok(upcomingEvents);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pastEvent")
    public ResponseEntity<List<Event>> getPastEvents() {
        List<Event> pastEvents = serviceEvent.getPastEvents();

        if (!pastEvents.isEmpty()) {
            return ResponseEntity.ok(pastEvents);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/Event-by-category")
    public ResponseEntity<List<Event>> filterEventsByCategory(@RequestParam String category) {
        List<Event> events = serviceEvent.filterEventsByCategory(category);

        if (!events.isEmpty()) {
            return ResponseEntity.ok(events);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

/*
    @GetMapping("/user/{idUser}")
    public ResponseEntity<Set<Event>> getRegisteredEventsForUser(@PathVariable Integer idUser) {
        Set<Event> registeredEvents = serviceEvent.getRegisteredEventsForUser(idUser);
        return ResponseEntity.ok(registeredEvents);
    }*/
}
