package hibuy.server.dto.userProduct;

import hibuy.server.domain.Status;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DailyUserProductDto {

    private Long userProductId;

    private String productName;

    private int oneTakeAmount;

    private Time takeTime;

    private Status status;

    public DailyUserProductDto(Long userProductId, String productName, int oneTakeAmount, Time takeTime, Status status) {
        this.userProductId = userProductId;
        this.productName = productName;
        this.oneTakeAmount = oneTakeAmount;
        this.takeTime = takeTime;
        this.status = status;
    }
}
