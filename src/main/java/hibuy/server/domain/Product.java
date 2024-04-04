package hibuy.server.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity{

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(nullable = false)
    private int price;

    @Setter
    @Column(name = "image_url")
    private String imageUrl;

    @Setter
    @Column(name = "product_url")
    private String productUrl;

    @Column(nullable = false)
    private String category;

    @Column(name = "one_take_amount")
    private int oneTakeAmount;

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "take_count")
    private int takeCount;

    @Builder
    private Product(String companyName, String productName, int price, String imageUrl, String productUrl,
                   String category, int oneTakeAmount, int totalAmount, int takeCount) {
        super();
        this.companyName = companyName;
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productUrl = productUrl;
        this.category = category;
        this.oneTakeAmount = oneTakeAmount;
        this.totalAmount = totalAmount;
        this.takeCount = takeCount;
    }

}
