package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.EldSync.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "SELECT u.* FROM user u ORDER BY (SELECT COUNT(*) FROM user_events ue WHERE ue.users_id_user = u.id_user) DESC LIMIT 1", nativeQuery = true)
    User findUserWithMostEvents();

}
