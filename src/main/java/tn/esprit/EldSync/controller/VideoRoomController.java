package tn.esprit.EldSync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.EldSync.model.VideoRoom;
import tn.esprit.EldSync.service.VideoRoomService;

import java.util.List;

@RestController
@RequestMapping("/v1/videoRooms")
public class VideoRoomController {

    @Autowired
    private VideoRoomService videoRoomService;

    @GetMapping
    public ResponseEntity<List<VideoRoom>> getAllVideoRooms() {
        List<VideoRoom> videoRooms = videoRoomService.getAllVideoRooms();
        return new ResponseEntity<>(videoRooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoRoom> getVideoRoomById(@PathVariable("id") Long id) {
        VideoRoom videoRoom = videoRoomService.getVideoRoomById(id);
        return new ResponseEntity<>(videoRoom, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VideoRoom> createVideoRoom(@RequestBody VideoRoom videoRoom) {
        VideoRoom createdVideoRoom = videoRoomService.createVideoRoom(videoRoom);
        return new ResponseEntity<>(createdVideoRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoRoom> updateVideoRoom(@PathVariable("id") Long id, @RequestBody VideoRoom videoRoom) {
        VideoRoom updatedVideoRoom = videoRoomService.updateVideoRoom(id, videoRoom);
        return new ResponseEntity<>(updatedVideoRoom, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoRoom(@PathVariable("id") Long id) {
        videoRoomService.deleteVideoRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/inviteCode/{inviteCode}")
    public ResponseEntity<VideoRoom> getVideoRoomByInviteCode(@PathVariable("inviteCode") String inviteCode) {
        VideoRoom videoRoom = videoRoomService.getVideoRoomByInviteCode(inviteCode);
        return new ResponseEntity<>(videoRoom, HttpStatus.OK);
    }
    @DeleteMapping("/{inviteCode}")
    public ResponseEntity<?> deleteVideoRoomByInviteCode(@PathVariable String inviteCode) {
        try {
            videoRoomService.deleteByInviteCode(inviteCode);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete video room.");
        }
    }
}
