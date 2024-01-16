package hibuy.server.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PostUserRequest {

    private String name;
    private String email;
    private String phoneNumber;

}
