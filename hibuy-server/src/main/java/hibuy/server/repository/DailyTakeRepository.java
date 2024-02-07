package hibuy.server.repository;

import hibuy.server.domain.DailyTake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface DailyTakeRepository extends JpaRepository<DailyTake, Long> {

    @Query("SELECT dt.takeDate FROM DailyTake dt JOIN dt.user u WHERE u.userId = :user_id AND u.status = 'ACTIVE' AND dt.status = 'ACTIVE'")
    List<Date> findTakeDatesByUserId(@Param("user_id") Long userId);
}
