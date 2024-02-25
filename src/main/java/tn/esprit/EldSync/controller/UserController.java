package tn.esprit.EldSync.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.EldSync.model.User;
import tn.esprit.EldSync.service.UserService;

import java.util.List;

@RestController
@RequestMapping("EldSync/User")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/AddUser")
    public User AddUser(@RequestBody User user){return userService.AddUser(user);}

    @PutMapping("/UpdateUser")
    public User UpdateUser(@RequestBody User user){return userService.AddUser(user);}

    @GetMapping("/FindAllUsers")
    public List<User> retrieveAllUsers(){return userService.retrieveAllUsers();}

    @GetMapping("/FindUserId/{idUser}")
    public  void retrieveUser(@PathVariable("idUser") Integer idUser){userService.retrieveUser(idUser);}

    @DeleteMapping("/DeleteUser/{idUser}")
    public void removeUser(@PathVariable("idUser") Integer idUser){userService.removeUser(idUser);}
}
