package az.ingress.model.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class TourResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<DestinationResponse> destinations;
    private Set<GuideResponse> guides;
    private Set<TravelerResponse> travelers;
}
