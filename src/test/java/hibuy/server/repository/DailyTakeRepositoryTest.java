package hibuy.server.repository;

import hibuy.server.domain.DailyTake;
import hibuy.server.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class DailyTakeRepositoryTest {

    @Autowired UserRepository userRepository;
    @Autowired DailyTakeRepository dailyTakeRepository;

    @DisplayName("userId로 takeDate를 조회한다.")
    @Test
    void findTakeDatesByUserId() {
        //given
        User user = User.builder()
                .kakaoUserId(1L)
                .email("abcd123@naver.com")
                .name("김철수")
                .phoneNumber("01012341234")
                .build();
        userRepository.save(user);

        DailyTake dailyTake = new DailyTake(Date.valueOf("2024-04-03"), user);
        DailyTake dailyTake2 = new DailyTake(Date.valueOf("2024-04-04"), user);
        dailyTakeRepository.saveAll(List.of(dailyTake, dailyTake2));

        //when
        List<Date> results = dailyTakeRepository.findTakeDatesByUserId(user.getUserId());

        //then
        assertThat(results).hasSize(2)
                .containsExactlyInAnyOrder(
                        Date.valueOf("2024-04-03"),
                        Date.valueOf("2024-04-04")
                );
    }

    @DisplayName("userId와 takeDate로 DailyTake를 조회한다.")
    @Test
    void findDailyTakeByUserIdAAndTakeDate() {
        //given
        User user = User.builder()
                .kakaoUserId(1L)
                .email("abcd123@naver.com")
                .name("김철수")
                .phoneNumber("01012341234")
                .build();
        userRepository.save(user);

        DailyTake dailyTake = new DailyTake(Date.valueOf("2024-04-03"), user);
        DailyTake dailyTake2 = new DailyTake(Date.valueOf("2024-04-04"), user);
        dailyTakeRepository.saveAll(List.of(dailyTake, dailyTake2));

        //when
        DailyTake savedDailyTake = dailyTakeRepository.findDailyTakeByUserIdAAndTakeDate(user.getUserId(), Date.valueOf("2024-04-04")).get();

        //then
        assertThat(dailyTake2).isEqualTo(savedDailyTake);
    }
}