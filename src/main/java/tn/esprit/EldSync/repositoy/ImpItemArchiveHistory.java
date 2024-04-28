package tn.esprit.EldSync.repositoy;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.EldSync.model.ItemArchive;

import java.util.Optional;

public interface ImpItemArchiveHistory extends JpaRepository<ItemArchive, Long> {
    @Query("SELECT ia FROM ItemArchive ia WHERE ia.idarchive = :idarchive")
    Optional<ItemArchive> findByIdarchive(Long idarchive);

    @Transactional
    @Modifying
    @Query("DELETE FROM ItemArchive ia WHERE ia.idarchive = :idarchive")
    void deleteByIdarchive(Long idarchive);
}
