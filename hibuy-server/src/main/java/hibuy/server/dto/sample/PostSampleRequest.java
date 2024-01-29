package hibuy.server.dto.sample;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PostSampleRequest {

    private String productName;

    private String companyName;

    private String imageUrl;

    private String productUrl;

}
