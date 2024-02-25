 package tn.esprit.EldSync.repositoy;

import tn.esprit.EldSync.model.ComplaintCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintCategoryRepository extends JpaRepository<ComplaintCategory, Integer> {
}