package tn.esprit.EldSync.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.EldSync.model.Notes;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Long> {
}
