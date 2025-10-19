package az.ingress.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PassportResponse {
    private Long id;
    private String passportNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String countryName;
}
