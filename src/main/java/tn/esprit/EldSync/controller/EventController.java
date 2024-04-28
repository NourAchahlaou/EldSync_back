package tn.esprit.EldSync.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.apache.coyote.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tn.esprit.EldSync.model.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.EldSync.service.ServiceEvent;


import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("EldSync/Event")
@RequiredArgsConstructor
public class EventController {

    private final ServiceEvent serviceEvent;



    @GetMapping("/eventBanner/{eventId}")
    public ResponseEntity<byte[]> getEventBanner(@PathVariable("eventId") Long eventId) {
        try {
            byte[] image = serviceEvent.getEventBannerById(eventId);
            if (image == null || image.length == 0) {
                return ResponseEntity.notFound().build();
            }
            String contentType = "image/jpeg"; // This could be dynamic
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(image);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{eventId}/approve")
    public ResponseEntity<Event> approveEvent(@PathVariable Long eventId) {
        Event event = serviceEvent.approveEvent(eventId);
        return ResponseEntity.ok(event);
    }


    @PostMapping("/addEvent")
    public ResponseEntity<?> addEvent(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("category") EventCategory category,
            @RequestParam("location") String location,
            @RequestParam("price") float price,
            @RequestParam("status") EventStatus status
    ) {
        try {
            String fileName = file != null ? StringUtils.cleanPath(file.getOriginalFilename()) : null;
            Path targetLocation = Paths.get("file-storage-path");
            if (!Files.exists(targetLocation)) {
                Files.createDirectories(targetLocation);
            }

            if (file != null && !file.isEmpty()) {
                targetLocation = targetLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            } else {
                return ResponseEntity.badRequest().body("File must not be empty.");
            }

            Event event = new Event();
            event.setName(name);
            event.setDescription(description);
            event.setBannerData(targetLocation.toString());
            event.setDate(date);
            event.setCategory(category);
            event.setLocation(location);
            event.setPrice(price);
            event.setStatus(status);

            Event savedEvent = serviceEvent.addEvent(event);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedEvent.getIdEvent())
                    .toUri();
            return ResponseEntity.created(uri).body(savedEvent);
        } catch (IOException ex) {
            return ResponseEntity.internalServerError().body("Error occurred while storing the file: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: " + e.getMessage());
        }
    }




    @PutMapping("/updateEvent/{idEvent}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long idEvent, @RequestPart("event") Event event, @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            Event updatedEvent = serviceEvent.updateEvent(idEvent, event, file);
            return ResponseEntity.ok(updatedEvent);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

  /*    @PutMapping("/updateEvent/{idEvent}")
        public Event updateEvent(@PathVariable Long idEvent, @RequestBody Event event) {return serviceEvent.updateEvent(idEvent, event);}

*/



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
    @GetMapping("/getItemDetails/{id}")


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
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

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
        if (pastEvents.isEmpty()) {
            return ResponseEntity.noContent().build(); // or ResponseEntity.ok().body(Collections.emptyList());
        }
        return ResponseEntity.ok(pastEvents);
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

    @GetMapping("/locationsSuggestions")
    public Mono<List<String>> getLocationSuggestions(@RequestParam String query) {
        return serviceEvent.getLocationSuggestions(query);
    }

}
