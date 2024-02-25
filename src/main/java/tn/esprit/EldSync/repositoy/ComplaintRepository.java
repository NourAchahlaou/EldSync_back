package tn.esprit.EldSync.repositoy;

import tn.esprit.EldSync.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
}