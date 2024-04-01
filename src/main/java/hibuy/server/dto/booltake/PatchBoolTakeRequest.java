package hibuy.server.dto.booltake;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

import hibuy.server.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchBoolTakeRequest {

    private Long userProductId;
    private Date takeDate;
    private Time takeTime;
    private Status status;

    public LocalDateTime getLocalDateTimeFromRequest() {
        return LocalDateTime.of(takeDate.toLocalDate(), takeTime.toLocalTime());
    }

}
