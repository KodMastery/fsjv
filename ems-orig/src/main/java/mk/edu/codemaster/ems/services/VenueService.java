package mk.edu.codemaster.ems.services;

import mk.edu.codemaster.ems.entities.Venue;
import mk.edu.codemaster.ems.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    public Venue getVenueById(Long venueId) {
        Optional<Venue> venue = venueRepository.findById(venueId);
        if(venue.isPresent()) {
            return venue.get();
        }else {
            throw new IllegalArgumentException("Venue " + venueId + " does not exist");
        }
    }

    public List<Venue> getVenues() {
        return venueRepository.findAll();
    }

    public Venue saveVenue(String name, String address, int capacity) {
        return venueRepository.save(new Venue(name, address, capacity));
    }

    public Venue updateVenue(Long id, String address, int capacity) {
        Venue venue = getVenueById(id);
        venue.setAddress(address);
        venue.setCapacity(capacity);
        return venueRepository.save(venue);
    }

    public void deleteVenueById(Long id) {
        venueRepository.deleteById(id);
    }


}
