package az.ingress.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassportRequest {
    private String passportNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String countryName;
}
