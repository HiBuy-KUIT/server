package hibuy.server.dto.sample;

import hibuy.server.domain.Sample;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter @Setter
@NoArgsConstructor
public class PostSampleRequest {

    private String productName;

    private String companyName;

    @URL
    private String imageUrl;

    @URL
    private String productUrl;

    public Sample toEntity() {
        return Sample.builder()
                .productName(productName)
                .companyName(companyName)
                .imageUrl(imageUrl)
                .productUrl(productUrl)
                .build();
    }

}
