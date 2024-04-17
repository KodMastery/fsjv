package mk.edu.codemaster.online.shop.mappers;

import mk.edu.codemaster.online.shop.entities.User;
import mk.edu.codemaster.online.shop.entities.dtos.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public User convertFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        return user;
    }

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public List<UserDTO> convertToDTOList(List<User> users) {
        return users.stream().map(this::convertToDTO).toList();
    }

}
