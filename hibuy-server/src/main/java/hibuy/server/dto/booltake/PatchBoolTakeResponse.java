package hibuy.server.dto.booltake;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchBoolTakeResponse {

    private Long userProductId;
    private Timestamp takeDateTime;
}
