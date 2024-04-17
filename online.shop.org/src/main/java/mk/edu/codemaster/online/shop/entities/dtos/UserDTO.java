package mk.edu.codemaster.online.shop.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull(message = "FullName is required")
    @Size(min = 3, message = "Minimum FullName length is 3")
    private String fullName;
    @NotNull(message = "Username is required")
    @Size(min = 3, message = "Minimum username length is 3")
    private String username;
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Minimum password length is 6 characters")
    private String password;

}
