package mk.edu.codemaster.ems.services;

import mk.edu.codemaster.ems.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired private EventRepository eventRepository;

}
