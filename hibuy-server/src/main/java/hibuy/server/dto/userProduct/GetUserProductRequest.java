package hibuy.server.dto.userProduct;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserProductRequest {

    private Timestamp takeTimestamp;

    private Long userId;

}