package hibuy.server.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DateCountTest {

    private DateCount dateCount;

    @BeforeEach
    void init() {
        User user = User.builder()
                .kakaoUserId(1L)
                .email("abcd123@naver.com")
                .name("김철수")
                .phoneNumber("01012341234")
                .build();

        dateCount = new DateCount(user);
    }

    @DisplayName("유저 등록 시 누적 섭취일은 0부터 시작한다.")
    @Test
    void startsWithZeroDateCount() {
        //then
        assertThat(dateCount.getDateCount()).isZero();
    }

    @DisplayName("유저가 하루 섭취량을 모두 채우면 누적 섭취일은 1 증가한다.")
    @Test
    void increaseDateCount() {
        //when
        dateCount.increaseDateCount();

        //then
        assertThat(dateCount.getDateCount()).isEqualTo(1);
    }

    @DisplayName("누적 섭취일은 최대 150일이다. 150일 다음은 1부터 시작한다.")
    @Test
    void backToZeroWhenDateCountGetOver150() {
        //when
        for(int i=1; i<=151; i++) {
            dateCount.increaseDateCount();
        }

        //then
        assertThat(dateCount.getDateCount()).isEqualTo(1);
    }

}