package hibuy.server.dto.dateCount;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetDateCountResponse {
    private Long userId;
    private int dateCount;
}
