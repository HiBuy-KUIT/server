package hibuy.server.dto.address;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostAddressRequest {

    public static final String PHONE_NUMBER_REGEX = "^010\\d{8}$";

    @NotEmpty(message = "유효하지 않은 이름 입력 형식입니다.")
    private String addressName;

    private String receiver;

    @Pattern(regexp = PHONE_NUMBER_REGEX, message = "유효한 전화번호 형식이 아닙니다.")
    private String phoneNumber;

    @NotEmpty
    private String zipCode;

    @NotEmpty
    private String basicAddress;

    @NotEmpty
    private String detailAddress;

    private Boolean isDefaultAddress;

    private String request;

    private Long userId;

}
