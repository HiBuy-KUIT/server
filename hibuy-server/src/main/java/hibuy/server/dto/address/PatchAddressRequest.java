package hibuy.server.dto.address;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchAddressRequest {
    @NotNull
    private Long addressId;

    @NotNull
    private Long userId;

    private String request;
}
