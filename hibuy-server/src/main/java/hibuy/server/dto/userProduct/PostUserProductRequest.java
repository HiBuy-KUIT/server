package hibuy.server.dto.userProduct;

import java.sql.Time;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserProductRequest {

    //1회 섭취량
    private int oneTakeAmount;

    //총 제품량
    private int totalAmount;

    //섭취 시간
    private List<Time> takeTimeList;

    //섭취 요일
    private int takeDay;

    //알림 여부
    private int notification;

    private Long userId;

    private Long productId;
}