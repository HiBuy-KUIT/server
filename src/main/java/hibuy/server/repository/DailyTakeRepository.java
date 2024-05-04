package hibuy.server.repository;

import hibuy.server.domain.DailyTake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface DailyTakeRepository extends JpaRepository<DailyTake, Long> {

    @Query("SELECT dt.takeDate FROM DailyTake dt JOIN dt.user u WHERE u.userId = :userId AND u.status = 'ACTIVE' AND dt.status = 'ACTIVE'")
    List<Date> findTakeDatesByUserId(@Param("userId") Long userId);

    @Query("SELECT dt FROM DailyTake dt JOIN dt.user u " +
            "WHERE u.userId = :userId AND u.status = 'ACTIVE' AND dt.takeDate = :takeDate")
    Optional<DailyTake> findDailyTakeByUserIdAAndTakeDate(@Param("userId") Long userId, @Param("takeDate") Date takeDate);
}
