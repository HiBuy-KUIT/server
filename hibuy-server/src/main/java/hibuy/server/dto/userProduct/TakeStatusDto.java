package hibuy.server.dto.userProduct;

import java.sql.Time;

import hibuy.server.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TakeStatusDto {

    private Time takeTime;
    private Status status;
}
