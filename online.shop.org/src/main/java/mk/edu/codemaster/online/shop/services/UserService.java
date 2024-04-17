package mk.edu.codemaster.online.shop.services;

import mk.edu.codemaster.online.shop.entities.User;
import mk.edu.codemaster.online.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new IllegalStateException("User with that username already exists");
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("User with that email already exists");
        }
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updateUser) {
        User user = getUserById(id);
        if(updateUser.getUsername() != null && !user.getUsername().equals(updateUser.getUsername())){
            if(userRepository.findByUsername(updateUser.getUsername()).isPresent()){
                throw new IllegalStateException("User with that username already exists");
            }else{
                user.setUsername(updateUser.getUsername());
            }
        }
        if(updateUser.getPassword() != null && !user.getPassword().equals(updateUser.getPassword())) {
            user.setPassword(updateUser.getPassword());
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
