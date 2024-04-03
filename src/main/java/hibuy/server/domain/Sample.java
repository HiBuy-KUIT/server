package hibuy.server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sample extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sample_id")
    private Long sampleId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "product_url")
    private String productUrl;

    @Builder
    private Sample(String productName, String companyName, String imageUrl, String productUrl) {
        super();
        this.productName = productName;
        this.companyName = companyName;
        this.imageUrl = imageUrl;
        this.productUrl = productUrl;
    }

}
