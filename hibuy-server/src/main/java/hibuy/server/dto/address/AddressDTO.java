package hibuy.server.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressDTO {
    private Long addressId;
    private String addressName;
    private String receiver;
    private String phoneNumber;
    private String basicAddress;
    private String detailAddress;
    private Boolean isDefaultAddress;
}
