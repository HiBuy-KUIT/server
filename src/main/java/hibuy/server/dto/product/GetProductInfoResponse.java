package hibuy.server.dto.product;

import hibuy.server.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetProductInfoResponse {
    public String productName;
    public String companyName;
    public int price;
    public int oneTakeAmount;
    public int totalAmount;
    public int takeCount;
    public String imageUrl;

    public static GetProductInfoResponse from(Product product) {
        return GetProductInfoResponse.builder()
                .productName(product.getProductName())
                .companyName(product.getCompanyName())
                .price(product.getPrice())
                .oneTakeAmount(product.getOneTakeAmount())
                .totalAmount(product.getTotalAmount())
                .takeCount(product.getTakeCount())
                .imageUrl(product.getImageUrl())
                .build();
    }
}
