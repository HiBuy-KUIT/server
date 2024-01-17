package hibuy.server.dto.dailyTake;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
@NoArgsConstructor
public class PostDailyTakeRequest {
    private Date date;
}
