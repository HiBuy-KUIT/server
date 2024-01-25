package hibuy.server.dto.userProduct;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetHomeUserProductsRequest {

    private Timestamp takeTimestamp;

    private Long userId;

}
