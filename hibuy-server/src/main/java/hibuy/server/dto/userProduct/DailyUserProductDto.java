package hibuy.server.dto.userProduct;

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

//        private String status;
//
//    private List<Time> takeTime;

    private List<TakeStatusDto> takeStatusDtoList;


    public DailyUserProductDto(Long userProductId, String productName, int oneTakeAmount) {
        this.userProductId = userProductId;
        this.productName = productName;
        this.oneTakeAmount = oneTakeAmount;
        this.takeStatusDtoList = new ArrayList<>();
    }
}
