package hibuy.server.dto.userProduct;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetHomeUserProductsRequest {

    private LocalDate takeDate;

    private Long userId;

}
