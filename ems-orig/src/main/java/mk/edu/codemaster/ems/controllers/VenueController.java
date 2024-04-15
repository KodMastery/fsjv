package mk.edu.codemaster.ems.controllers;

import mk.edu.codemaster.ems.dto.VenueDTO;
import mk.edu.codemaster.ems.dto.mapper.VenueMapper;
import mk.edu.codemaster.ems.entities.Venue;
import mk.edu.codemaster.ems.services.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/venue")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @Autowired
    VenueMapper venueMapper;

    @GetMapping("/{id}")
    public ResponseEntity<VenueDTO> getVenueById(@PathVariable Long id) {
        Venue venue = venueService.getVenueById(id);
        return ResponseEntity.ok().body(venueMapper.toDto(venue));
    }

    @GetMapping
    public ResponseEntity<List<VenueDTO>> getAllVenues() {
        List<Venue> venues = venueService.getVenues();
        return ResponseEntity.ok().body(venueMapper.toDtoList(venues));
    }

    @PostMapping
    public ResponseEntity<VenueDTO> createVenue(@RequestBody VenueDTO venue) {
        Venue savedVenue = venueService.saveVenue(venue.getName(), venue.getAddress(), venue.getCapacity());
        return ResponseEntity.status(HttpStatus.CREATED).body(venueMapper.toDto(savedVenue));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenueDTO> updateVenue(@PathVariable Long id, @RequestBody VenueDTO venue) {
        Venue updatedVenue = venueService.updateVenue(id, venue.getAddress(), venue.getCapacity());
        return ResponseEntity.ok().body(venueMapper.toDto(updatedVenue));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VenueDTO> deleteVenue(@PathVariable Long id) {
        venueService.deleteVenueById(id);
        return ResponseEntity.noContent().build();
    }

}
