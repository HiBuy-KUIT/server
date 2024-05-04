package hibuy.server.repository;

import hibuy.server.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired UserRepository userRepository;

    @DisplayName("카카오 ID로 유저를 조회한다.")
    @Test
    void findByKakaoUserId() {
        //given
        User user = createUser(1L, "김철수", "abcd123@naver.com", "01012341234");
        User user2 = createUser(2L, "박철수", "abcd1234@naver.com", "01023452345");
        userRepository.saveAll(List.of(user, user2));

        //when
        User savedUser = userRepository.findByKakaoUserId(1L).get();

        //then
        Assertions.assertThat(savedUser).isEqualTo(user);
    }

    private User createUser(Long kakaoUserId, String name, String email, String phoneNumber) {
        return User.builder()
                .kakaoUserId(kakaoUserId)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}