package mk.edu.codemaster.ems.repositories;

import mk.edu.codemaster.ems.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
