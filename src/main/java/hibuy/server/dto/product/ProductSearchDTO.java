package hibuy.server.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductSearchDTO {
    private String productName;
    private int oneTakeAmount;
    private int totalAmount;
    private int takeCount;
}
