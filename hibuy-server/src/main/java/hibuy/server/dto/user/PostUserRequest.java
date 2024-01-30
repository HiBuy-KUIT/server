package hibuy.server.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PostUserRequest {

    public static final String PHONE_NUMBER_REGEX = "^010\\d{8}$";

    @NotEmpty(message = "유효하지 않은 이름 입력 형식입니다.")
    private String name;

    @Email
    private String email;

    @Pattern(regexp = PHONE_NUMBER_REGEX, message = "유효하지 않은 휴대폰 번호 입력 형식입니다.")
    private String phoneNumber;

}
