package hibuy.server.dto.sample;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetSampleResponse {

    private String productName;

    private String companyName;

    private String imageUrl;

    private String productUrl;

}
