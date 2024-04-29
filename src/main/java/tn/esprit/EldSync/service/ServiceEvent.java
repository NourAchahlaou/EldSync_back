package tn.esprit.EldSync.service;

import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;
import tn.esprit.EldSync.model.Event;
import tn.esprit.EldSync.model.EventStatus;
import tn.esprit.EldSync.Entity.User;
import tn.esprit.EldSync.repositoy.IEventRepository;
import tn.esprit.EldSync.Repo.UserRepo;
import tn.esprit.EldSync.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.JsonNode;



@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceEvent  {
    private final IEventRepository eventRepository;
    private final UserRepo userRepository;
    private final Path fileStorageLocation = Paths.get("file-storage-path").toAbsolutePath().normalize();
    private WebClient webClient;


    @Value("${opencage.api.key}")
    private String apiKey;

    @PostConstruct
    public void initWebClient() {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(10)); // Sets the response timeout to 10 seconds

        this.webClient = WebClient.builder()
                .baseUrl("https://api.opencagedata.com/geocode/v1/json")
                .build();
    }

    public Mono<List<String>> getLocationSuggestions(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", query)
                        .queryParam("key", apiKey)
                        .queryParam("pretty", 1)
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> jsonNode.path("results")
                        .findValues("formatted")
                        .stream()
                        .map(JsonNode::asText)
                        .collect(Collectors.toList()));
    }

    public Event approveEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setStatus(EventStatus.APPROVED);
        return eventRepository.save(event);
    }





    @Transactional
    public Event updateEvent(Long idEvent, Event event, MultipartFile file) throws IOException {
        Event existingEvent = eventRepository.findById(idEvent)
                .orElseThrow(() -> new RuntimeException("Event not found with id " + idEvent));

        // Handle file upload
        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path targetLocation = Paths.get("file-storage-path").toAbsolutePath().normalize();

            // Ensure directory exists
            if (!Files.exists(targetLocation)) {
                Files.createDirectories(targetLocation);
            }

            // Resolve the file path
            targetLocation = targetLocation.resolve(fileName);

            // Copy file to the target location (Replacing existing file with the same name)
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Set the bannerData path
            existingEvent.setBannerData(targetLocation.toString());
        } else {
            throw new IllegalArgumentException("File must not be empty.");
        }

        // Update event details
        existingEvent.setName(event.getName());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setLocation(event.getLocation());
        existingEvent.setDate(event.getDate());
        existingEvent.setCategory(event.getCategory());
        existingEvent.setStatus(event.getStatus());
        existingEvent.setPrice(event.getPrice());

        // Save updated event
        return eventRepository.save(existingEvent);
    }

    
    public Path handleFileStorage(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            // Ensure directory exists
            if (!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }

            // Construct new file name and path to avoid file name conflicts
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path targetLocation = fileStorageLocation.resolve(fileName);

            // Copy file to the target location, replacing existing file with the same name
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return targetLocation;
        } else {
            throw new IOException("File must not be empty.");
        }
    }


/*
    public Event updateEvent( Long idEvent,Event event) {
        return eventRepository.findById(idEvent).map(existingItem -> {
            existingItem.setName(event.getName());
            existingItem.setDescription(event.getDescription());
            existingItem.setLocation(event.getLocation());
            existingItem.setDate(event.getDate());
            existingItem.setBannerData(event.getBannerData());
            existingItem.setCategory(event.getCategory());
            existingItem.setStatus(event.getStatus());
            existingItem.setPrice(event.getPrice());
            Event updatedEvent = eventRepository.save(existingItem);
            return updatedEvent;
        }).orElseThrow(() -> new RuntimeException("Item not found with id " + idEvent));
    }
*/

    // In your ServiceEvent class
    public byte[] getEventBannerById(Long eventId) throws Exception {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new Exception("Event not found"));
        Path imagePath = Paths.get(event.getBannerData());
        if (!Files.exists(imagePath)) {
            throw new Exception("Image file not found");
        }
        return Files.readAllBytes(imagePath);

    }




    @Transactional
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

/*
    public Event addEvent (Event event){return eventRepository.save(event);};
*/
    public  void retrieveEvent(Long idEvent){ eventRepository.findById(idEvent);}
    public List <Event> retrieveAllEvents(){return eventRepository.findAll();}
    
    public void removeEvent(Long idEvent){eventRepository.deleteById(idEvent);}
    
    public Event suggestNewEvent(Event event) {
        event.setStatus(EventStatus.PENDING);
        return eventRepository.save(event);
    }

    public Event getEventDetails(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }
    public List<Event> filterEventsByCategory(String category) {return eventRepository.findByCategory(category);}

    @Transactional

    public void registerUserForEvent(Integer idUser, Long eventId) {
        User user = userRepository.findById(String.valueOf(idUser)).orElseThrow(() -> new EntityNotFoundException("User not found"));
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

    public List<Event> getEventsByDonationDateRange(LocalDate fromDate, LocalDate toDate) {
        return eventRepository.findByDateBetween(fromDate, toDate);
    }


    public List<Event> getUpcomingEvents() {
        Date currentDate = new Date();
        return eventRepository.findByDateAfter(currentDate);
    }

    public List<Event> getPastEvents() {
        LocalDate currentDate = LocalDate.now();  // Get the current date as LocalDate
        return eventRepository.findByDateBefore(currentDate);
    }

/*
    @Transactional
    public Event updateEvent(Event updatedEvent) {
        Long eventId = updatedEvent.getIdEvent();
        Event existingEvent = eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found"));

        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setBanner(updatedEvent.getBanner());
        existingEvent.setDate(updatedEvent.getDate());
        existingEvent.setCategory(updatedEvent.getCategory());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setPrice(updatedEvent.getPrice());
        existingEvent.setStatus(updatedEvent.getStatus());

        return eventRepository.save(existingEvent);
    }
*/

}




