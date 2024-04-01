package hibuy.server.dto.userProduct;

import hibuy.server.domain.User;
import hibuy.server.domain.UserProduct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.sql.Time;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserProductRequest {

    @NotBlank(message = "제품 이름을 입력해주세요.")
    private String productName;

    //1회 섭취량
    @Positive
    @NotNull(message = "1회 섭취량을 입력해주세요.")
    private int oneTakeAmount;

    //총 제품량
    @Positive
    @NotNull(message = "드실 제품의 총량을 입력해주세요.")
    private int totalAmount;

    //섭취 시간
    @NotNull(message = "제품을 섭취하실 시간을 선택해주세요.")
    private List<Time> takeTimeList;

    @NotNull(message = "제품을 섭취하실 요일을 선택해주세요.")
    private List<Integer> takeDay;

    //알림 여부
    private int notification;

    private Long userId;

    private String companyName;

    private String imageUrl;

    public UserProduct toEntity(User user) {
        return UserProduct.createUserProductWithZeroTakeCount(oneTakeAmount, totalAmount, notification, productName, user, companyName, imageUrl);
    }
}