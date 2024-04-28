package tn.esprit.EldSync.Repo;

import tn.esprit.EldSync.Entity.Services;
import tn.esprit.EldSync.Enum.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<Services, Integer> {
    Services findByServiceType(ServiceType serviceType);
}