package az.ingress.model.response;

import lombok.Data;

@Data
public class GuideResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private PassportResponse passport;
}
