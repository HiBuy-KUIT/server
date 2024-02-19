package hibuy.server.dto.sample;

import hibuy.server.domain.Sample;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetSampleResponse {

    private String productName;

    private String companyName;

    private String imageUrl;

    private String productUrl;

    public GetSampleResponse(Sample sample) {
        this.productName = sample.getProductName();
        this.companyName = sample.getCompanyName();
        this.imageUrl = sample.getImageUrl();
        this.productUrl = sample.getProductUrl();
    }

}
