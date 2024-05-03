package tn.esprit.EldSync.Repo;


import org.springframework.data.jpa.repository.Query;
import tn.esprit.EldSync.Entity.User;
import tn.esprit.EldSync.Enum.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User,String> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);



    User findUserById(Long id);/*id user*/
   // List<User> findUsersByFollowerUsers(User user, Pageable pageable);
    //List<User> findUsersByFollowingUsers(User user, Pageable pageable);


   /* @Query(value = "select * from users u " +
            "where concat(u.first_name, ' ', u.last_name) like %:name% " +
            "order by u.first_name asc, u.last_name asc",
            nativeQuery = true)
    List<User> findUsersByName(String name, Pageable pageable);*/

    List<User> findUsersByStatus(Status status);
    Boolean existsByEmail(String email);


    User findIdByUsername(String username);


    User findByEmail(String email);

    @Query(value = "SELECT u.* FROM user u ORDER BY (SELECT COUNT(*) FROM user_events ue WHERE ue.users_id_user = u.id_user) DESC LIMIT 1", nativeQuery = true)
    User findUserWithMostEvents();

}
