package hibuy.server.dto.oauth2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;

}
