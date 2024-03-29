package hibuy.server.repository;

import hibuy.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.kakaoUserId = :kakaoUserId")
    Optional<User> findByKakaoUserId(@Param("kakaoUserId") Long kakaoUserId);

}
