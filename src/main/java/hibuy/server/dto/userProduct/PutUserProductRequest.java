package hibuy.server.dto.userProduct;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.sql.Time;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PutUserProductRequest {

    private Long userProductId;

    //1회 섭취량
    @Positive
    @NotBlank(message = "1회 섭취량을 입력해주세요.")
    private int oneTakeAmount;

    //총 제품량
    @Positive
    @NotBlank(message = "드실 제품의 총량을 입력해주세요.")
    private int totalAmount;

    //섭취 시간
    @NotBlank(message = "제품을 섭취하실 시간을 선택해주세요.")
    private List<Time> takeTimeList;

    @NotBlank(message = "제품을 섭취하실 요일을 선택해주세요.")
    private List<Integer> takeDay;

    //알림 여부
    private int notification;
}
