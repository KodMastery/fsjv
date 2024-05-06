package mk.edu.codemaster.online.shop.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import mk.edu.codemaster.online.shop.entities.User;
import mk.edu.codemaster.online.shop.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    // Create a mock object for the UserRepository
    @Mock
    private UserRepository userRepository;

    // Create a mock object for the UserRepository
    @Mock
    private PasswordEncoder passwordEncoder;

    // Injecting mock into the UserService
    @InjectMocks
    private UserService userService;

    private User existingUser;

    @BeforeEach
    void setUp() {
        existingUser = User.builder()
                .id(1L)
                .username("oldUsername")
                .password("oldPassword")
                .fullName("John Doe")
                .email("john.doe@example.com")
                .build();

        when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    void updateUser_ChangeUsername_NoConflict() {
        User updateUser = User.builder()
                .id(1L)
                .username("newUsername")
                .password("oldPassword")
                .fullName("John Doe")
                .email("john.doe@example.com")
                .build();

        when(userRepository.findByUsername("newUsername")).thenReturn(Optional.empty());

        User updatedUser = userService.updateUser(existingUser.getId(), updateUser);

        verify(userRepository).save(existingUser);
        assertEquals("newUsername", updatedUser.getUsername());
    }

    @Test
    void updateUser_ChangeUsername_WithConflict() {
        User updateUser = new User();
        updateUser.setUsername("existingUsername");

        when(userRepository.findByUsername("existingUsername")).thenReturn(Optional.of(new User()));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            userService.updateUser(existingUser.getId(), updateUser);
        });

        assertEquals("User with that username already exists", exception.getMessage());
    }

    @Test
    void updateUser_ChangePassword_ValidChange() {
        User updateUser = new User();
        updateUser.setPassword("newPassword");

        when(passwordEncoder.matches(updateUser.getPassword(), existingUser.getPassword())).thenReturn(false);
        when(passwordEncoder.encode(updateUser.getPassword())).thenReturn("encodedNewPassword");

        when(userRepository.save(updateUser)).thenReturn(updateUser);

        User updatedUser = userService.updateUser(existingUser.getId(), updateUser);

        verify(userRepository).save(updatedUser);
        assertEquals("encodedNewPassword", updatedUser.getPassword());
    }

    @Test
    void getUserById_UserNotFound() {
        Long userId = 99L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            userService.getUserById(userId);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void createUser_createNewUser_NoConflict() {
        User petko = User.builder()
                .id(2L)
                .username("petar_petko")
                .password("petar_petko")
                .fullName("Petar Petko")
                .email("petar.petko@gmail.com")
                .build();

        when(userRepository.findByUsername(petko.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(petko.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(petko.getPassword())).thenReturn("encoded_password");

        User newPetko = userService.createUser(petko);

        assertNotNull(newPetko);
        assertEquals(petko.getUsername(), newPetko.getUsername());
        assertEquals(petko.getEmail(), newPetko.getEmail());
        assertEquals(petko.getFullName(), newPetko.getFullName());
        assertEquals(newPetko.getPassword(), "encoded_password");
    }
}
