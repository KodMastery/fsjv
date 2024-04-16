package mk.edu.codemaster.ems.services;

import mk.edu.codemaster.ems.repositories.EventRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mk.edu.codemaster.ems.entities.Event;


@Service
public class EventService {

    @Autowired private EventRepository eventRepository;
    
    public List<Event> getAllEvents() {
      return eventRepository.findAll();
  }

  public Event getEventById(Long id) {
      return eventRepository.findById(id).orElse(null);
  }

  public Event createEvent(Event event) {
      // Add any validation or additional logic here if needed
      return eventRepository.save(event);
  }

  public void deleteEvent(Long id) {
      eventRepository.deleteById(id);
  }
}


