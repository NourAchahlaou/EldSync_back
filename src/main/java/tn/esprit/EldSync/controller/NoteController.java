package tn.esprit.EldSync.controller;
import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.EldSync.model.Notes;
import tn.esprit.EldSync.service.NoteService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<Notes> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notes> getNoteById(@PathVariable Long id) {
        Optional<Notes> optionalNote = noteService.getNoteById(id);
        if (optionalNote.isPresent()) {
            return ResponseEntity.ok(optionalNote.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/addNote")
    public ResponseEntity<?> addNote(
            @RequestParam(name = "image", required = false) MultipartFile image,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
        try {
            byte[] imageData = null;
            if (image != null && !image.isEmpty()) {
                imageData = image.getBytes();
            }

            Notes note = new Notes();
            note.setTitle(title);
            note.setContent(content);
            note.setImageData(imageData);

            Notes savedNote = noteService.createOrUpdateNote(note);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedNote.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(savedNote);
        } catch (IOException ex) {
            return ResponseEntity.internalServerError().body("Error occurred while storing the image: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notes> updateNote(@PathVariable Long id, @RequestBody Notes note) {
        Optional<Notes> optionalExistingNote = noteService.getNoteById(id);
        if (optionalExistingNote.isPresent()) {
            Notes existingNote = optionalExistingNote.get();
            note.setId(id);
            Notes updatedNote = noteService.createOrUpdateNote(note);
            return ResponseEntity.ok(updatedNote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNoteById(@PathVariable Long id) {
        try {
            noteService.deleteNoteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
