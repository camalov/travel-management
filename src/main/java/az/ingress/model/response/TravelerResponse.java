package az.ingress.model.response;

import lombok.Data;

@Data
public class TravelerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
