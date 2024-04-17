package mk.edu.codemaster.online.shop.controllers.rest;

import jakarta.validation.Valid;
import mk.edu.codemaster.online.shop.entities.User;
import mk.edu.codemaster.online.shop.entities.dtos.UserDTO;
import mk.edu.codemaster.online.shop.mappers.UserMapper;
import mk.edu.codemaster.online.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserMapper userMapper;
    @Autowired private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userMapper.convertToDTO(userService.getUserById(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getALllUsers(){
        return ResponseEntity.ok().body(userMapper.convertToDTOList(userService.getAllUsers()));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        User user = userService.createUser(userMapper.convertFromDTO(userDTO));
        UserDTO responseDTO = userMapper.convertToDTO(user);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO){
        User updatedUser = userService.updateUser(id, userMapper.convertFromDTO(userDTO));
        UserDTO responseDTO = userMapper.convertToDTO(updatedUser);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }


}
