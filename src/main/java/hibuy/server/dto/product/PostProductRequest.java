package hibuy.server.dto.product;


import hibuy.server.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter @Setter
@NoArgsConstructor
public class PostProductRequest {

    private String companyName;

    private String productName;

    private int price;

    @URL
    private String imageUrl;

    @URL
    private String productUrl;

    private String category;

    private int oneTakeAmount;

    private int totalAmount;

    private int takeCount;

    public Product toEntity() {
        return Product.of(companyName, productName, price, imageUrl, productUrl,
                category, oneTakeAmount, totalAmount, takeCount);
    }

}
