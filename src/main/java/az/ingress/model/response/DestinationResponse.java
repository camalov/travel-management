package az.ingress.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DestinationResponse {
    private Long id;
    private String location;
    private String description;
    private LocalDate visitDate;
}
