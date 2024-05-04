package hibuy.server.service;

import hibuy.server.common.exception.notfound.NotFoundDateCountException;
import hibuy.server.common.exception.notfound.NotFoundUserException;
import hibuy.server.domain.DateCount;
import hibuy.server.domain.User;
import hibuy.server.dto.dateCount.GetDateCountResponse;
import hibuy.server.repository.DateCountRepository;
import hibuy.server.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DateCountServiceTest {

    @Autowired DateCountService dateCountService;
    @Autowired DateCountRepository dateCountRepository;
    @Autowired UserRepository userRepository;

    @AfterEach
    void tearDown() {
        dateCountRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @DisplayName("유저의 누적 섭취일을 조회한다.")
    @Test
    void getUserDateCount() {
        //given
        User user = User.builder()
                .kakaoUserId(1L)
                .email("abcd123@naver.com")
                .name("김철수")
                .phoneNumber("01012341234")
                .build();
        userRepository.save(user);
        dateCountRepository.save(new DateCount(user));

        //when
        GetDateCountResponse response = dateCountService.getUserDateCount(user.getUserId());

        //then
        assertThat(response).extracting("userId", "dateCount")
                .containsExactly(user.getUserId(), 0);
    }

    @DisplayName("유저의 누적 섭취일 조회시 등록된 누적 섭취일이 없는 경우 예외를 발생시킨다..")
    @Test
    void searchDateCountThrowNotFoundExceptionWhenDateCountNotExists() {
        //given
        User user = User.builder()
                .kakaoUserId(1L)
                .email("abcd123@naver.com")
                .name("김철수")
                .phoneNumber("01012341234")
                .build();
        userRepository.save(user);

        //when //then
        assertThatThrownBy(() -> dateCountService.getUserDateCount(user.getUserId()))
                .isInstanceOf(NotFoundDateCountException.class)
                .hasMessage("해당 누적일수가 존재하지 않습니다.");
    }

    @DisplayName("누적 섭취일이 1 증가한다.")
    @Test
    void increaseDateCount() {
        //given
        User user = User.builder()
                .kakaoUserId(1L)
                .email("abcd123@naver.com")
                .name("김철수")
                .phoneNumber("01012341234")
                .build();
        userRepository.save(user);
        dateCountRepository.save(new DateCount(user));

        //when
        dateCountService.updateUserDateCount(user.getUserId());
        DateCount dateCount = dateCountRepository.findDateCountByUserId(user.getUserId()).get();

        //then
        Assertions.assertThat(dateCount.getDateCount()).isEqualTo(1);
    }

    @DisplayName("유저의 누적 섭취일 업데이트 시 유저가 존재하지 않는 경우 예외를 발생시킨다.")
    @Test
    void updateDateCountThrowNotFoundExceptionWhenDateCountNotExists() {
        //given
        User user = User.builder()
                .kakaoUserId(1L)
                .email("abcd123@naver.com")
                .name("김철수")
                .phoneNumber("01012341234")
                .build();
        userRepository.save(user);

        //when //then
        assertThatThrownBy(() -> dateCountService.updateUserDateCount(user.getUserId()))
                .isInstanceOf(NotFoundDateCountException.class)
                .hasMessage("해당 누적일수가 존재하지 않습니다.");
    }

}