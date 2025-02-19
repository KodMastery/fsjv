package mk.edu.codemaster.online.shop.services;

import jakarta.servlet.http.HttpSession;
import mk.edu.codemaster.online.shop.entities.User;
import mk.edu.codemaster.online.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        if(updateUser.getPassword() != null && !passwordEncoder.matches(updateUser.getPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean userExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException("User not found1"));
    }

    public boolean isLoggedIn(HttpSession session){
        return session.getAttribute("user") != null;
    }

}
