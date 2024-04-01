package hibuy.server.dto.booltake;

import java.time.LocalDateTime;

import hibuy.server.domain.BoolTake;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchBoolTakeResponse {

    private Long userProductId;
    private LocalDateTime takeDateTime;

    public static PatchBoolTakeResponse of(BoolTake boolTake, LocalDateTime localDateTime) {
        return new PatchBoolTakeResponse(boolTake.getUserProduct().getId(), localDateTime);
    }
}
