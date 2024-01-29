package hibuy.server.dto.userProduct;

import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TakeStatusDto {

    private Time takeTime;
    private String status;
}
