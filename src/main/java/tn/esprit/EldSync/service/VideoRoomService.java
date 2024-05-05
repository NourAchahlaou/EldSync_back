package tn.esprit.EldSync.service;

import tn.esprit.EldSync.model.VideoRoom;

import java.util.List;

public interface VideoRoomService {
        List<VideoRoom> getAllVideoRooms();
        VideoRoom getVideoRoomById(Long id);
        VideoRoom createVideoRoom(VideoRoom videoRoom);
        VideoRoom updateVideoRoom(Long id, VideoRoom videoRoom);
        void deleteVideoRoom(Long id);
        VideoRoom getVideoRoomByInviteCode(String inviteCode); // New method


        void deleteByInviteCode(String inviteCode);
}
