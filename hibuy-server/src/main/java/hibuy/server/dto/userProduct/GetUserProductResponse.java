package hibuy.server.dto.userProduct;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserProductResponse {

    private List<DailyUserProductDto> userProductDtoList;
}
