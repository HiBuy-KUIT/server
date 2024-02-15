package hibuy.server.dto.booltake;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchBoolTakeResponse {

    private Long userProductId;
    private LocalDateTime takeDateTime;
}
