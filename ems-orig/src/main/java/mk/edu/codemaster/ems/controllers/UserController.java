package mk.edu.codemaster.ems.controllers;

import mk.edu.codemaster.ems.entities.User;
import mk.edu.codemaster.ems.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping()
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    public User createUser(@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("age") int age){
        return userService.saveUser(new User(email, name, age));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestParam("age") int age){
        return userService.updateUser(id, new User(null, null, age));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/email")
    public User getUserByEmail(@RequestParam("email") String email){
        return userService.getUserByEmail(email);
    }
}
