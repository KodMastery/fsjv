package mk.edu.codemaster.online.shop.repositories;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import mk.edu.codemaster.online.shop.entities.User;
import mk.edu.codemaster.online.shop.services.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    @Autowired private UserRepository userRepository;


    @Test
    public void UserRepository_CreateUser_ReturnSavedUser(){

        //Arrange
        User user = User.builder()
                .email("user@example.com")
                .fullName("User Name")
                .password("password")
                .username("username").build();

        //Act
        User savedUser = userRepository.save(user);


        //Assert
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
        Assertions.assertThat(savedUser.getEmail()).isEqualTo("user@example.com");
    }

    @Test
    void UserRepository_GetAllUsers_ReturnAllUsers(){

        User user1 = User.builder()
                .email("user1@example.com")
                .fullName("User1 Name")
                .password("password1")
                .username("username1").build();
        User user2 = User.builder()
                .email("user2@example.com")
                .fullName("User2 Name")
                .password("password2")
                .username("username2").build();

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> savedUsers = userRepository.findAll();

        Assertions.assertThat(savedUsers.size()).isEqualTo(2);

    }

}
