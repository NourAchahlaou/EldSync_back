package tn.esprit.EldSync.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.EldSync.execption.ResourceNotFoundException;
import tn.esprit.EldSync.model.VideoRoom;
import tn.esprit.EldSync.repositoy.VideoRoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VideoRoomServiceImpl implements VideoRoomService {

    @Autowired
    private VideoRoomRepository videoRoomRepository;

    @Override
    public List<VideoRoom> getAllVideoRooms() {
        return videoRoomRepository.findAll();
    }

    @Override
    public VideoRoom getVideoRoomById(Long id) {
        return videoRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VideoRoom", "id", id));
    }

    @Override
    public VideoRoom createVideoRoom(VideoRoom videoRoom) {
        return videoRoomRepository.save(videoRoom);
    }

    @Override
    public VideoRoom updateVideoRoom(Long id, VideoRoom videoRoom) {
        VideoRoom existingVideoRoom = getVideoRoomById(id);
        existingVideoRoom.setId(videoRoom.getId());
        // You can update other properties here
        return videoRoomRepository.save(existingVideoRoom);
    }

    @Override
    public void deleteVideoRoom(Long id) {
        VideoRoom videoRoom = getVideoRoomById(id);
        videoRoomRepository.delete(videoRoom);
    }
    @Override
    public VideoRoom getVideoRoomByInviteCode(String inviteCode) {
        return videoRoomRepository.findByInviteCode(inviteCode);
    }
    @Transactional
    public void deleteByInviteCode(String inviteCode) {
        Optional<VideoRoom> optionalVideoRoom = Optional.ofNullable(videoRoomRepository.findByInviteCode(inviteCode));
        if (optionalVideoRoom.isPresent()) {
            VideoRoom videoRoom = optionalVideoRoom.get();
            videoRoomRepository.delete(videoRoom);
        } else {
            throw new ResourceNotFoundException("VideoRoom", "inviteCode", inviteCode);
        }
    }
}
