package hibuy.server.dto.address;

import hibuy.server.domain.Address;
import lombok.Getter;

@Getter
public class AddressDTO {
    private Long addressId;
    private String addressName;
    private String receiver;
    private String phoneNumber;
    private String basicAddress;
    private String detailAddress;
    private Boolean isDefaultAddress;

    public AddressDTO(Address address) {
        this.addressId = address.getId();
        this.addressName = address.getAddressName();
        this.receiver = address.getReceiver();
        this.phoneNumber = address.getPhoneNumber();
        this.basicAddress = address.getBasicAddress();
        this.detailAddress = address.getDetailAddress();
        this.isDefaultAddress = address.isDefaultAddress();
    }
}
