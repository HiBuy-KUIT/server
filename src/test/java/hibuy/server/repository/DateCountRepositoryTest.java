package hibuy.server.repository;

import hibuy.server.domain.DateCount;
import hibuy.server.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class DateCountRepositoryTest {

    @Autowired DateCountRepository dateCountRepository;
    @Autowired UserRepository userRepository;

    @DisplayName("사용자의 누적 섭취량을 조회한다.")
    @Test
    void findDateCountByUserId() {
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
        DateCount dateCount = dateCountRepository.findDateCountByUserId(user.getUserId()).get();

        //then
        Assertions.assertThat(dateCount.getDateCount()).isZero();
    }

}