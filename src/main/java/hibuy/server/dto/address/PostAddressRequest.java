package hibuy.server.dto.address;

import hibuy.server.domain.Address;
import hibuy.server.domain.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
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

    private Boolean defaultAddress;

    private String request;

    private Long userId;

    public Address toEntity(User user) {
        return Address.builder()
                .addressName(addressName)
                .receiver(receiver)
                .phoneNumber(phoneNumber)
                .zipCode(zipCode)
                .basicAddress(basicAddress)
                .detailAddress(detailAddress)
                .defaultAddress(defaultAddress)
                .request(request)
                .user(user)
                .build();
    }

}
