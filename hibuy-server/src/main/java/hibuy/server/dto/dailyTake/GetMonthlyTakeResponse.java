package hibuy.server.dto.dailyTake;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetMonthlyTakeResponse {

    private List<Date> intakeDate;

}
