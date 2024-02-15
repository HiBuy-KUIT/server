package hibuy.server.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetProductInfoResponse {
    public String productName;
    public int oneTakeAmount;
    public int totalAmount;
    public int takeCount;
}
