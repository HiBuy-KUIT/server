package hibuy.server.dto.userProduct;

import java.sql.Time;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PutUserProductRequest {

    private Long userProductId;

    //1회 섭취량
    private int oneTakeAmount;

    //총 제품량
    private int totalAmount;

    //섭취 시간
    private List<Time> takeTimeList;

    private List<Integer> takeDay;
    //알림 여부
    private int notification;

    private Long userId;

    private Long productId;
}
