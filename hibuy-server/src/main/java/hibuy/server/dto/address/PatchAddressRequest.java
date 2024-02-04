package hibuy.server.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchAddressRequest {
    private Long addressId;
    private String request;
}
