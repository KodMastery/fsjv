package mk.edu.codemaster.ems.repositories;

import mk.edu.codemaster.ems.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    public Optional<User> getUserWithEmail(@Param("email") String email);

}
