package tn.esprit.EldSync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import tn.esprit.EldSync.Entity.Role;
import tn.esprit.EldSync.Enum.ERole;
import tn.esprit.EldSync.Repo.RoleRepo;

@SpringBootApplication
public class EldSyncApplication {




	@Autowired
	RoleRepo roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(EldSyncApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	@Bean
	CommandLineRunner start() {
		return args -> {

			for (ERole r1 : ERole.values()
			) {

				if (!roleRepository.existsByName(r1)) {
					Role r = new Role(r1);
					roleRepository.save(r);
				}
			}

		};
	}

}
