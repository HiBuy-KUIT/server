package hibuy.server.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private String companyName;
    private int price;
    private String imageUrl;
    private String productUrl;
}
