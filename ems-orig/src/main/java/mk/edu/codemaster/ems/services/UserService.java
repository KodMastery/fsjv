package mk.edu.codemaster.ems.services;

import mk.edu.codemaster.ems.entities.User;
import mk.edu.codemaster.ems.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public User getUserById(Long id) {
//        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found"));
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new IllegalStateException("User not found");
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User uUser){
        User user = getUserById(id);
        user.setAge(uUser.getAge());
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        try {
            userRepository.deleteById(id);
        }catch (DataAccessException e){
            throw new RuntimeException("User not found with id: " + id, e);
        }
    }

    public User getUserByEmail(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        if(user.isPresent()){
            return user.get();
        }else {
            throw new RuntimeException("User with email: " + email + " does not exists!");
        }
    }

}
