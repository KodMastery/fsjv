package mk.edu.codemaster.ems.dto.mapper;

import mk.edu.codemaster.ems.dto.VenueDTO;
import mk.edu.codemaster.ems.entities.Venue;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VenueMapper {

    public VenueDTO toDto(Venue venue) {
        VenueDTO dto = new VenueDTO();
        dto.setId(venue.getId());
        dto.setName(venue.getName());
        dto.setAddress(venue.getAddress());
        dto.setCapacity(venue.getCapacity());
        return dto;
    }

    public Venue toEntity(VenueDTO dto) {
        Venue venue = new Venue();
        venue.setId(dto.getId());
        venue.setName(dto.getName());
        venue.setAddress(dto.getAddress());
        venue.setCapacity(dto.getCapacity());
        return venue;
    }

    public List<VenueDTO> toDtoList(List<Venue> venues) {
        return venues.stream().map(this::toDto).collect(Collectors.toList());
    }

}

