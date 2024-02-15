package hibuy.server.dto.product;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetProductListResponse {
    List<ProductDTO> productDTOs;
}
