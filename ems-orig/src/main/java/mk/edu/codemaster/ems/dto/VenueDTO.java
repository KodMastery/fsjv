package mk.edu.codemaster.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VenueDTO {

    private Long id;
    private String name;
    private String address;
    private int capacity;

}
