package hibuy.server.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product {

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

    @Column
    private String status;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Product(String companyName, String productName, int price, String imageUrl, String productUrl, String category) {
        this.companyName = companyName;
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productUrl = productUrl;
        this.category = category;
        this.status = "ACTIVE";
    }

}
