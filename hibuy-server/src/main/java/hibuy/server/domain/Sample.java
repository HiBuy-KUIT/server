package hibuy.server.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Sample {

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

    @Column
    private String status;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Sample(String productName, String companyName, String imageUrl, String productUrl) {
        this.productName = productName;
        this.companyName = companyName;
        this.imageUrl = imageUrl;
        this.productUrl = productUrl;
        this.status = "ACTIVE";
    }

}
