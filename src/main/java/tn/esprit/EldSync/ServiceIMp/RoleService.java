package tn.esprit.EldSync.ServiceIMp;


import tn.esprit.EldSync.Entity.Role;
import tn.esprit.EldSync.Repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    public Role createNewRole(Role role) {
        return roleRepo.save(role);
    }
}
