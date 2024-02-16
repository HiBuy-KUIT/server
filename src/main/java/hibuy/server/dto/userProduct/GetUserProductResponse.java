package hibuy.server.dto.userProduct;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserProductResponse {

    /**
     * CompanyName ProductName ImageUrl TImeList DayList OneTakeAmount
     */
    private Long userProductId;
    private String productName;
    private String imageUrl;
    private List<Time> timeList;
    private List<String> dayList;
    private int oneTakeAmount;

    public GetUserProductResponse(Long userProductId, String productName, String imageUrl,
            int oneTakeAmount) {
        this.userProductId = userProductId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.oneTakeAmount = oneTakeAmount;
    }

    public void setTimeList(List<Time> timeList) {
        this.timeList = timeList;
    }

    public void setDayList(List<Integer> dayList) {
        this.dayList = toString(dayList);
    }

    public List<String> toString(List<Integer> dayList) {
        List<String> result = new ArrayList<>();
        for (Integer day : dayList) {
            result.add(Day.getDayByValue(day).toString());
        }
        return result;
    }
}
