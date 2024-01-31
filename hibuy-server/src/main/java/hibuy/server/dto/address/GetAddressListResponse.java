package hibuy.server.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAddressListResponse {
    List<AddressDTO> addresses;
}
