package tn.esprit.EldSync.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.EldSync.model.User;
import tn.esprit.EldSync.repositoy.UserRepo;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;



    public User AddUser(User user){return userRepo.save(user);}
    public List <User> retrieveAllUsers(){return userRepo.findAll();}
    public void removeUser(Integer idUser){userRepo.deleteById(idUser);}
    public  void retrieveUser(Integer idUser){ userRepo.findById(idUser);}

}
