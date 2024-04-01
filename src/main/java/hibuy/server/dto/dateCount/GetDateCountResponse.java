package hibuy.server.dto.dateCount;

import hibuy.server.domain.DateCount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetDateCountResponse {
    private Long userId;
    private int dateCount;

    public static GetDateCountResponse of(DateCount dateCount) {
        return new GetDateCountResponse(dateCount.getUser().getUserId(), dateCount.getDateCount());
    }
}
