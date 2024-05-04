package hibuy.server.dto.dailyTake;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDailyTakeRequest {
    private Date date;
}
