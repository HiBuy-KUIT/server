package hibuy.server.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetProductSearchListResponse {
    private List<ProductSearchDTO> productSearchDTOS;
}
