package hibuy.server.service;

import hibuy.server.common.exception.notfound.NotFoundUserException;
import hibuy.server.controller.LoginController;
import hibuy.server.domain.DailyTake;
import hibuy.server.domain.User;
import hibuy.server.dto.dailyTake.GetMonthlyTakeResponse;
import hibuy.server.repository.DailyTakeRepository;
import hibuy.server.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class DailyTakeServiceTest {

    @Autowired DailyTakeService dailyTakeService;
    @Autowired DailyTakeRepository dailyTakeRepository;
    @Autowired UserRepository userRepository;

    @AfterEach
    void tearDown() {
        dailyTakeRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @DisplayName("입력 받은 년, 월과 일치하는 날짜를 오름차순으로 반환한다.")
    @Test
    void getMonthlyTake() {
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
        DailyTake dailyTake3 = new DailyTake(Date.valueOf("2024-04-01"), user);
        DailyTake dailyTakeWithDifferentYear = new DailyTake(Date.valueOf("2023-04-04"), user);
        DailyTake dailyTakeWithDifferentMonth = new DailyTake(Date.valueOf("2024-03-04"), user);
        dailyTakeRepository.saveAll(List.of(dailyTake, dailyTake2, dailyTake3, dailyTakeWithDifferentYear,dailyTakeWithDifferentMonth));

        String year = "2024";
        String month = "04";

        //when
        GetMonthlyTakeResponse monthlyTake = dailyTakeService.getMonthlyTake(user.getUserId(), year, month);
        List<Date> intakeDate = monthlyTake.getIntakeDate();

        //then
        assertThat(intakeDate).hasSize(3)
                .containsExactly(
                        Date.valueOf("2024-04-01"),
                        Date.valueOf("2024-04-03"),
                        Date.valueOf("2024-04-04")
                );
    }

    @DisplayName("dailyTake가 존재하지 않는 경우 새로 추가한다.")
    @Test
    void addDailyTakeWhenDailyTakeEmpty() {
        //given
        User user = User.builder()
                .kakaoUserId(1L)
                .email("abcd123@naver.com")
                .name("김철수")
                .phoneNumber("01012341234")
                .build();
        userRepository.save(user);

        List<Date> results = dailyTakeRepository.findTakeDatesByUserId(1L);
        assertThat(results).isEmpty();

        //when // then
        dailyTakeService.addDailyTake(user.getUserId(), Date.valueOf("2024-04-03"));
        results = dailyTakeRepository.findTakeDatesByUserId(user.getUserId());

        assertThat(results).containsExactly(Date.valueOf("2024-04-03"));
    }

    @DisplayName("dailyTake가 활성화 상태로 존재하는 경우 새로 추가하지 않는다.")
    @Test
    void NotAddDailyTakeWhenDailyTakeExists() {
        //given
        User user = User.builder()
                .kakaoUserId(1L)
                .email("abcd123@naver.com")
                .name("김철수")
                .phoneNumber("01012341234")
                .build();
        userRepository.save(user);

        DailyTake dailyTake = new DailyTake(Date.valueOf("2024-04-03"), user);
        dailyTakeRepository.save(dailyTake);

        List<Date> results = dailyTakeRepository.findTakeDatesByUserId(user.getUserId());
        assertThat(results).hasSize(1).containsExactly(Date.valueOf("2024-04-03"));

        //when
        dailyTakeService.addDailyTake(user.getUserId(), Date.valueOf("2024-04-03"));

        //then
        assertThat(results).hasSize(1).containsExactly(Date.valueOf("2024-04-03"));
    }

    @DisplayName("존재하지 않는 회원에 대한 dailytake 추가 처리는 예외를 발생시킨다.")
    @Test
    void addDailyTakeWithNotFoundUser() {
        //when //then
        assertThatThrownBy(() -> dailyTakeService.addDailyTake(1L, Date.valueOf("2024-04-03")))
                .isInstanceOf(NotFoundUserException.class)
                .hasMessage("존재하지 않는 회원입니다.");
    }
}