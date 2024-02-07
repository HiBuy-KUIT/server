package hibuy.server.dto.userProduct;

import java.sql.Time;

import hibuy.server.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProductDto {

    private Long userProductId;

    private String productName;

    private int oneTakeAmount;
}
