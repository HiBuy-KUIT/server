package hibuy.server.dto.product;


import hibuy.server.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetProductResponse {

    private List<Product> products;
}
