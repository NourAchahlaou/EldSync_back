package tn.esprit.EldSync.repositoy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.EldSync.model.Item;

public interface ImpItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByCategory(String category, Pageable pageable);


}
