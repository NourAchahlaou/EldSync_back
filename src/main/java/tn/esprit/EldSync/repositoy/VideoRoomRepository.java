package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.EldSync.model.VideoRoom;
import tn.esprit.EldSync.model.VitalSigns;

public interface VideoRoomRepository extends JpaRepository<VideoRoom, Long> {
    VideoRoom findByInviteCode(String inviteCode);
}
