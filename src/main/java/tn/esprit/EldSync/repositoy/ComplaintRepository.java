package tn.esprit.EldSync.repositoy;

import com.example.nouranmanagment.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
}