package hibuy.server.dto.userProduct;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetHomeUserProductsResponse {

    private List<DailyUserProductDto> userProductDtoList;
}
